import { Component, OnInit, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FileUploader } from 'ng2-file-upload';
import { MatTableDataSource } from '@angular/material/table';
import { TableComponent } from '../table/table-main/table.component';
import { DownloadService } from '../../services/download/download.service';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ProgressBarMode } from '@angular/material/progress-bar';

// const URL = 'http://10.1.34.94:80/api/v1/rnc/upload';
const URL = 'http://localhost:80/api/v1/rnc/upload';

@Component({
  selector: 'app-stepper',
  templateUrl: './stepper.component.html',
  styleUrls: ['./stepper.component.scss']
})
export class StepperComponent implements OnInit {

  filename = 'RncMaximoTable1.csv';
  lastFileName = 'RncMaximoTable1.csv';

  filename1: any;

  isLinear = false;
  firstFormGroup!: FormGroup;
  secondFormGroup!: FormGroup;
  
  headers = [];
  values: any = [];
  checkedValues = [];


  fileOfChanges;


  // visibility
  nextVisible: boolean = true;
  validateVisible: boolean = true;

  public uploader: FileUploader = new FileUploader({ url: URL, itemAlias: 'file' });

  // tslint:disable-next-line:variable-name
  constructor(private _formBuilder: FormBuilder, public service: DownloadService, public dialog: MatDialog) {

  }

  ngOnInit() {
    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });

    this.service.reCreateFileOfChanges().subscribe(data => {

      if (data.headers.length < 0) {
        this.headers = data.headers;
        this.values = data.values;
      }
    })

    // this.uploader.onAfterAddingFile = (file) => { file.withCredentials = false; this.isDivVisible = true; };

    this.uploader.onBeforeUploadItem = (file) => {
      this.lastFileName = this.filename;
      this.filename = file._file.name;
      console.log("onBeforeUploadItem", file);
    };
    this.uploader.onAfterAddingFile = (file) => {
      file.withCredentials = false;
    };
    this.uploader.onCompleteItem = (item: any, response: any, status: any, headers: any) => {
      console.log("onBeforeUploadItem", response);

      const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
        width: '150px',
        height: '150px',
        disableClose: true
      });

      if (JSON.parse(response).status) {

        let isFinished = false;
        this.service.getFileOfChanges(this.filename).subscribe(data => {

          console.log(data);

          if (data && data.headers) {
            this.fileOfChanges = data;

            isFinished = true;

            this.validateVisible = false;
            this.headers = data.headers;
            this.values = data.values;

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

        }, err => { console.log(err) });
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

    console.log("bofere upload file and before clear uploader queue");
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

    const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
      width: '150px',
      height: '150px',
      disableClose: true
    });

    this.checkedValues = this.fileOfChanges.values;
    this.service.validateRnc(this.fileOfChanges).subscribe(data => {
      dialogRef.close();
      

      this.checkedValues = data['values'];


      this.nextVisible = !this.arrayEquals(data['values'], this.values);
      console.log("array are equal - ", !this.nextVisible);
      
    }, error => {
      console.log(error);
    })
  }

  arrayEquals(a, b): boolean {
    return Array.isArray(a) &&
      Array.isArray(b) &&
      a.length === b.length &&
      a.every((val, index) => val === b[index]);
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