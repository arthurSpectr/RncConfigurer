import { Component, OnInit, Inject, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FileUploader } from 'ng2-file-upload';
import { MatTableDataSource } from '@angular/material/table';
import { TableComponent } from '../table/table-main/table.component';
import { DownloadService } from '../../services/download/download.service';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ProgressBarMode } from '@angular/material/progress-bar';
import { forkJoin } from 'rxjs';
import { GridComponent } from '../grid/grid.component';
import { Router } from '@angular/router';
import * as myGlobals from '../../services/globals';

const URL = myGlobals.API + 'v1/rnc/upload';

@Component({
  selector: 'app-stepper',
  templateUrl: './stepper.component.html',
  styleUrls: ['./stepper.component.scss']
})
export class StepperComponent implements OnInit {

  @ViewChild(GridComponent) child: GridComponent;

  filename = 'RncMaximoTable1.csv';
  lastFileName = 'RncMaximoTable1.csv';
  // alarmFile = 'alarmTextFile';
  // siteAlarmFile = 'siteAlarmTextFile';

  filename1: any;

  isLinear = false;
  firstFormGroup!: FormGroup;
  secondFormGroup!: FormGroup;

  headers = [];
  defaultValues = [];
  availableValues = [];
  validatedValues = [];


  fileOfChanges;


  // visibility
  secondStepDisabled: boolean = true;
  validateVisible: boolean = true;
  linkToAlarmFile: boolean = false;

  public uploader: FileUploader = new FileUploader({ url: URL, itemAlias: 'file' });

  // tslint:disable-next-line:variable-name
  constructor(private _formBuilder: FormBuilder, public service: DownloadService, public dialog: MatDialog, private router: Router) { }

  ngOnInit() {
    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });

    this.service.reCreateFileOfChanges().subscribe(data => {

      if (data && data.headers && data.headers.length > 0) {
        this.fileOfChanges = data;

        this.validateVisible = false;
        this.headers = data.headers;
        this.defaultValues = data.values;

        let tablePanel = document.querySelector(".table__panel");
        if (tablePanel) {
          tablePanel.setAttribute("style", "display:block");
        }

        let tableComponent = document.querySelector(".tableComponent");
        if (tableComponent) {
          tableComponent.setAttribute("style", "display:block");
        }

        forkJoin(
          this.service.reCreateAvailableFileOfChanges(),
          this.service.reCreateValidatedFileOfChanges()

        ).subscribe(([availableValues, validatedValues]) => {
          console.log("recreate validated file of changes — ", availableValues);
          console.log("recreate available file of changes — ", validatedValues);

          if (availableValues.headers.length > 0) {
            this.availableValues = availableValues['values'];
          }

          if (validatedValues.headers.length > 0) {
            this.validatedValues = validatedValues['values'];
          }

          if (availableValues.headers.length > 0 && validatedValues.headers.length > 0) {
            this.linkToAlarmFile = true;


            this.secondStepDisabled = !this.isValid(this.validatedValues);
            console.log('in recreate file secondStepDisabled = ', this.secondStepDisabled);
          }
        }, error => {
          console.log(error);
        });
      }
    })



    // this.service.reCreateFileOfChanges().subscribe(data => {

    //   if (data.headers.length > 0) {
    //     console.log("if headers  > 0  ", data);

    //     this.validateVisible = false;
    //     this.headers = data.headers;
    //     this.values = data.values;

    //     let tablePanel = document.querySelector(".table__panel");
    //     if (tablePanel) {
    //       tablePanel.setAttribute("style", "display:block");
    //     }

    //     let tableComponent = document.querySelector(".tableComponent");
    //     if (tableComponent) {
    //       tableComponent.setAttribute("style", "display:block");
    //     }
    //   }
    // })

    // this.uploader.onAfterAddingFile = (file) => { file.withCredentials = false; this.isDivVisible = true; };
    // this.uploader.onErrorItem = (item: any, response: any, status: any, headers: any) => {
    //   alert(response.message);
    // };

    let dialogRef;

    this.uploader.onBeforeUploadItem = (file) => {
      this.lastFileName = this.filename;
      this.filename = file._file.name;

      dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
        width: '150px',
        height: '150px',
        disableClose: true
      });
    };
    this.uploader.onAfterAddingFile = (file) => {
      file.withCredentials = false;
    };
    this.uploader.onCompleteItem = (item: any, response: any, status: any, headers: any) => {

      if (JSON.parse(response).status) {

        let isFinished = false;
        this.service.getFileOfChanges(this.filename).subscribe(data => {

          console.log(data);

          if (data && data.headers) {
            this.fileOfChanges = data;

            isFinished = true;

            this.validateVisible = false;
            this.headers = data.headers;
            this.defaultValues = data.values;
            this.availableValues = [];
            this.validatedValues = [];

            let tablePanel = document.querySelector(".table__panel");
            if (tablePanel) {
              tablePanel.setAttribute("style", "display:block");
            }

            let tableComponent = document.querySelector(".tableComponent");
            if (tableComponent) {
              tableComponent.setAttribute("style", "display:block");
            }

            dialogRef.close();
          } else {
            dialogRef.close();
            console.log("getting error list from server   ", data)

            alert(data.response)
            return;
          }

        }, err => { console.log(err); dialogRef.close(); });
      } else {
        alert('validation was not passed, choose another file');
      }

    };
  }

  browse() {
    (<HTMLInputElement>document.getElementById('selectedFile')).value = null;

    let httpElem = document.getElementById('selectedFile');
    if (httpElem) {
      httpElem.click();
    }

    this.uploader.clearQueue();
  }

  downloadFiles() {

    return this.service.getFiles().subscribe(data => {
      this.saveFile(data.body, data.headers.get('content-disposition'));
    }, error => {
      console.log('Error during download file');
      console.log(error);
    }), () => console.log('OK');
  }

  saveFile(data: Blob, filename: string) {

    const name = filename.split('"');
    const blob = new Blob([data], { type: 'application/zip' });
    const a = document.createElement('a');
    a.href = window.URL.createObjectURL(blob);
    a.download = name[1];
    a.click();
  }

  validateFile() {
    this.secondStepDisabled = true;

    const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
      width: '150px',
      height: '150px',
      disableClose: true
    });

    this.validatedValues = this.fileOfChanges.values;

    forkJoin(
      this.service.validateRnc(this.fileOfChanges),
      this.service.checkAvailableparams(this.fileOfChanges)
    ).subscribe(([res1, res2]) => {
      console.log("validatedValues — ", res1);
      console.log("availableValues — ", res2);


      this.linkToAlarmFile = true;
      this.validatedValues = res1['values'];
      this.availableValues = res2['values'];

      this.secondStepDisabled = !this.isValid(this.validatedValues);

      dialogRef.close();
      console.log("all requests are completed")
    }, error => {
      dialogRef.close();
      console.log(error);
    });

  }

  goToFile(fileName: string) {
    this.router.navigate(['fileViewer'], { queryParams: { file: fileName } });
  }

  // arrayEquals(a, b): boolean {
  //   return Array.isArray(a) &&
  //     Array.isArray(b) &&
  //     a.length === b.length &&
  //     a.every((val, index) => val.every((val1, index1) => val1 === b[index][index1]) );
  // }

  isValid(validatedValues): boolean {
    console.log('validated values in method isValid - ', validatedValues)

    let result = true;

    if (validatedValues instanceof Array) {

      validatedValues.forEach(row => {
        row.forEach(cell => {
          
          if (cell === false) {
            console.log("when cell is false in isValid method");
            result = false;
          }
        });
      });
    } 

    return result;
  }

  // isValid(arr1, arr2): boolean {

  //   let result = false;
  //   let step = false;

  //   for (var i = 0; i < arr1.length; i++) {

  //     for (var q = 0; q < arr1[i].length; q++) {

  //       // if(!this.validatedValues[i][q]) return false;

  //       if (Array.isArray(arr2[i][q])) {
  //         if (arr1[i][q] == arr2[i][q][0]) {

  //           if (!this.validatedValues[i][q]) {
  //             return false;
  //           }

  //         }

  //         step = false;


  //         for (var c = 0; c < arr2[i][q].length; c++) {
  //           if (arr2[i][q][c] == arr1[i][q]) {
  //             step = true;
  //           }
  //         }

  //         if (!step) {
  //           return false;
  //         }
  //       }

  //     }

  //   }

  //   return true;
  // }

  arrayEquals(arr1, arr2): boolean {

    let result = true;

    if (!Array.isArray(arr1) || !Array.isArray(arr2)) {
      result = false;
    }

    if (arr1.length !== arr2.length) {
      result = false;
    }

    for (var i = 0; i < arr1.length; i++) {
      if (arr1[i].length !== arr2[i].length) {
        result = false;
      }
    }

    for (var i = 0; i < arr1.length; i++) {

      for (var q = 0; q < arr1[i].length; q++) {
        var res = arr1[i][q] === arr2[i][q];

        if (!res) {
          result = false;
        }
      }

    }


    return result;
  }


  isAllTableValuesValid(validateValuesEvent) {
    console.log(validateValuesEvent)
    this.secondStepDisabled = !this.isValid(validateValuesEvent.validatedValues);
    console.log("in isAllTableValuesValid - disabled = ", this.secondStepDisabled)
  }

}

@Component({
  selector: 'dialog-overview-example-dialog',
  templateUrl: 'dialog-overview-example-dialog.html',
  styleUrls: ['dialog-overview-example-dialog.scss']
})
export class DialogOverviewExampleDialog {

  constructor(
    public dialogRef: MatDialogRef<DialogOverviewExampleDialog>) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

}