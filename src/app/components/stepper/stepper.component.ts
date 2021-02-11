import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {FileUploader} from 'ng2-file-upload';
import {MatTableDataSource} from '@angular/material/table';
import {TableComponent} from '../table/table-main/table.component';
import {DownloadService} from '../../services/download/download.service';


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
  visible = true;
  headers = [];
  values: any = [];
  checkedValues = [];

  public uploader: FileUploader = new FileUploader({url: URL, itemAlias: 'file'});

  // tslint:disable-next-line:variable-name
  constructor(private _formBuilder: FormBuilder, public service: DownloadService) {
    console.log(this.filename);
  }

  ngOnInit() {
    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });

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
      if (JSON.parse(response).status) {
        this.service.getFileOfChanges(this.filename).subscribe(data => {
          this.visible = false;

          this.headers = data.headers;
          this.values = data.values;
          console.log(this.filename);
          console.log(data);
          console.log(data.values);

          // if(data.length >= 8) {
            let tablePanel = document.querySelector(".table__panel");
            if(tablePanel) {
              tablePanel.setAttribute("style", "display:block");
            }
          // }
          let tableComponent = document.querySelector(".tableComponent");
          if(tableComponent) {
            tableComponent.setAttribute("style", "display:block");
          }
          // document.getElementsByClassName(".tableComponent").item(0).style.display = "block";
          alert('File uploaded successfully');

        });
      } else {
        alert('validation was not passed, choose another file');
      }

    };
  }

  browse() {
    let httpElem = document.getElementById('selectedFile');
    if(httpElem) {
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
    const blob = new Blob([data], {type: 'application/zip'});
    const a = document.createElement('a');
    a.href = window.URL.createObjectURL(blob);
    a.download = name[1];
    a.click();
  }

  validateFile() {
    this.service.validateRnc().subscribe(data => {
      this.values = data['values'];
      this.checkedValues = data['values'];
      console.log(data);
    }, error => {
      console.log(error);
    })
  }

}
