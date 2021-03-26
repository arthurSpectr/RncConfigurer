import { Component, OnInit, Inject, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FileUploader } from 'ng2-file-upload';
import { DownloadService } from '../../services/download/download.service';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { GridComponent } from '../grid/grid.component';
import { Router } from '@angular/router';
import * as myGlobals from '../../services/globals';
import { RncModification, RowOfChanges } from 'src/app/model/rnc-modification';
import { CookieService } from 'ngx-cookie-service';
import { UploadService } from 'src/app/services/upload/upload.service';

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

  rncModification: RncModification = new RncModification();


  // visibility
  secondStepDisabled: boolean = true;
  validateVisible: boolean = true;
  linkToAlarmFile: boolean = false;

  public uploader: FileUploader = new FileUploader({ url: URL, itemAlias: 'file' });

  // tslint:disable-next-line:variable-name
  constructor(private _formBuilder: FormBuilder, public service: DownloadService, public uploadService: UploadService, public dialog: MatDialog, private router: Router, private cookieService: CookieService) { }

  ngOnInit() {
    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });

    this.service.reCreateFileOfChanges().subscribe(response => {


      let data = response.body;

      console.log(data)

      if (data != null && data.headers && data.headers.length > 0) {




        this.validateVisible = false;
        console.log("trying to recreate file of changes - ", data.values)

        this.rncModification = data;
        console.log("rncmodification - ", this.rncModification);

        let tablePanel = document.querySelector(".table__panel");
        if (tablePanel) {
          tablePanel.setAttribute("style", "display:block");
        }

        let tableComponent = document.querySelector(".tableComponent");
        if (tableComponent) {
          tableComponent.setAttribute("style", "display:block");
        }


        this.service.reCreateValidatedFileOfChanges().subscribe(rncModif => {


          if (rncModif != null && rncModif.body != null) {

            if (rncModif.body.headers.length > 0) {

              this.rncModification = rncModif.body;

              this.linkToAlarmFile = true;


              this.secondStepDisabled = !this.isValid(this.rncModification.values);
              console.log("from recreate file of changes, isValid - ", this.secondStepDisabled);
              console.log('in recreate file secondStepDisabled = ', this.secondStepDisabled);
            }
          }


        }, error => {
          console.log(error);
        })

      }
    })


    // this.uploader.onAfterAddingFile = (file) => { file.withCredentials = false; this.isDivVisible = true; };
    // this.uploader.onErrorItem = (item: any, response: any, status: any, headers: any) => {
    //   alert(response.message);
    // };

    let dialogRef;



  }

  uploadFile(event) {

    let dialogRef;

    dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
      width: '150px',
      height: '150px',
      disableClose: true
    });


    let fileList: FileList = event.target.files;

    if (fileList.length > 0) {
      let file: File = fileList[0];

      console.log('in upload file, file - ', file);



      let formData: FormData = new FormData();
      formData.append('file', file, file.name);

      this.uploadService.upload(formData).subscribe(response => {

        // console.log('from upload, response - ', response)

        dialogRef.close();

        let rncModif = response.body;

        if (rncModif.headers.length > 0) {

          this.rncModification = rncModif;
          // console.log("rncmodification - ", this.rncModification);


          this.validateVisible = false;

          let tablePanel = document.querySelector(".table__panel");
          if (tablePanel) {
            tablePanel.setAttribute("style", "display:block");
          }

          let tableComponent = document.querySelector(".tableComponent");
          if (tableComponent) {
            tableComponent.setAttribute("style", "display:block");
          }


        } else {
          dialogRef.close();
          console.log("getting error list from server   ")

          alert(rncModif)
          return;
        }
      });


    }
  }

  browse() {
    (<HTMLInputElement>document.getElementById('selectedFile')).value = null;

    let httpElem = document.getElementById('selectedFile');
    if (httpElem) {
      httpElem.click();
    }

    this.uploader.clearQueue();
  }

  performIubUtranCell() {
    console.log("in performIubUtranCell method")

    const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
      width: '150px',
      height: '150px',
      disableClose: true
    });

    return this.service.performIubUtranCell().subscribe(data => {

      dialogRef.close();

      this.saveFile(data.body, data.headers.get('content-disposition'));
    }, error => {
      dialogRef.close();
      console.log('Error during download file');
      console.log(error);
    }), () => console.log('OK');
  }

  performExternalNeighbours() {
    console.log("in performExternalNeighbours method")

    const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
      width: '150px',
      height: '150px',
      disableClose: true
    });

    return this.service.performExternalNeighbours().subscribe(data => {

      dialogRef.close();

    }, error => {
      dialogRef.close();
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

    // this.validatedValues = this.fileOfChanges.values;

    this.service.validateRnc(this.rncModification).subscribe(data => {

      this.linkToAlarmFile = true;
      // this.validatedValues = res1.values;
      // this.availableValues = res2.values;

      // this.fileOfChanges = validatedValues;

      // this.defaultValues = validatedValues['values'];


      let rncModif = data.body;
      this.rncModification = rncModif;

      console.log("headers from validate - ", this.rncModification.headers)

      this.secondStepDisabled = !this.isValid(this.rncModification.values);
      console.log("from validateRnc, isValid - ", this.secondStepDisabled);

      dialogRef.close();


    }, error => {
      dialogRef.close();
      console.log(error)
    });


    // forkJoin(
    //   this.service.validateRnc(this.fileOfChanges),
    //   this.service.checkAvailableparams(this.fileOfChanges)
    // ).subscribe(([validatedValues, availableValues]) => {
    //   console.log("validatedValues — ", validatedValues);
    //   console.log("availableValues — ", availableValues);


    //   this.linkToAlarmFile = true;
    //   // this.validatedValues = res1.values;
    //   // this.availableValues = res2.values;

    //   this.fileOfChanges = validatedValues;
    //   this.defaultValues = validatedValues['values'];


    //   this.secondStepDisabled = !this.isValid(this.defaultValues);

    //   dialogRef.close();
    //   console.log("all requests are completed")
    // }, error => {
    //   dialogRef.close();
    //   console.log(error);
    // });

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

  isValid(rows: Array<RowOfChanges>): boolean {

    console.log("from isValid, rows - ", rows);

    let result = true;

    if (rows instanceof Array) {

      rows.forEach(row => {
        row.cells.forEach(cell => {

          if (cell.valid === false) {
            result = false;
          }
        });
      });

      // for(let i = 0; i< rows.length; i++) {

      //   let rowCells = rows[i].cells;

      //   for(let q = 0; q < rowCells.length; q++) {

      //     let cell = rowCells[q].isValid;

      //     console.log("from isValid cell.isValid - ", rowCells[q])
      //     if(cell === false) {
      //       return false;
      //     }

      //   }

      // }

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
    console.log("from isAllTableValuesValid, isValid - ", this.secondStepDisabled);
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