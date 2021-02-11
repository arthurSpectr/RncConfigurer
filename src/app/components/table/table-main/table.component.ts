import {ChangeDetectorRef, Component, ElementRef, OnDestroy, OnInit, ViewChild, ViewEncapsulation} from '@angular/core';
import {DownloadService} from '../../../services/download/download.service';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {FileUploader} from 'ng2-file-upload';
import {MatDialog} from '@angular/material/dialog';
import {map} from 'rxjs/operators';
import {Observable} from 'rxjs';
// import {ShowResultComponent} from '../../show-result/show-result.component';
import {MatTableDataSource} from '@angular/material/table';
import {MatSnackBar} from '@angular/material/snack-bar';

export interface PeriodicElement {
  RehomOrder: string;
  BSC: string;
  Site: string;
  Cell: string;
  LON: string;
  LAT: string;
  LAC: string;
  CI: string;
  NewBSC: string;
  NewLAC: string;
  NewCI: string;
}

// const URL = 'http://10.1.34.94:80/api/uploadFile';
const URL = 'http://localhost:80/api/uploadFile';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class TableComponent implements OnInit, OnDestroy {
  static filename = 'RncMaximoTable1.csv';
  static lastFileName = 'RncMaximoTable1.csv';
  static objects: object;

  @ViewChild('scrollTo', { static: false })
  MyProp!: ElementRef;

  names = [];
  headers: Array<any> = [];

  displayedColumns: string[] = ['Rehom.Order', 'BSC', 'Site', 'Cell', 'LON', 'LAT', 'LAC', 'CI', 'New BSC', 'New LAC', 'New CI', 'New Ura', 'New_RBSID_1', 'New_RBSID_2'];
  dataSource = new MatTableDataSource<string>();


  form: FormGroup | undefined;

  isUploaded = false;

  // to send file to back end
  fileData!: File;
  previewUrl: any = null;

  mySubscription: any;
  valid = true;
  classColor = 'success';
  notFoundRncList: Array<string> | undefined;
  fileUploadProgress: string | undefined;
  uploadedFilePath: string | undefined;

  public uploader: FileUploader = new FileUploader({ url: URL, itemAlias: 'file' });

  constructor(
    public activatedRoute: ActivatedRoute,
    private downloadService: DownloadService,
    private route: ActivatedRoute,
    private http: HttpClient,
    public fb: FormBuilder,
    public dialog: MatDialog,
    public router: Router,
    private snackBar: MatSnackBar,
    private changeDetectorRefs: ChangeDetectorRef
  ) {
    // this.route.queryParams.subscribe(params => {
    //   TableComponent.filename = params.fileName;
    //   if (!(typeof params.exist === 'undefined')) {
    //     this.valid = params.exist;
    //   }
    //   console.log(params);
    // });
    // const state: Observable<object> = this.router.getCurrentNavigation().extras.state;
  }

  ngOnInit() {
    this.uploader.onBeforeUploadItem = (file) => {
      TableComponent.lastFileName = TableComponent.filename;
      TableComponent.filename = file._file.name;
    };
    this.uploader.onAfterAddingFile = (file) => { file.withCredentials = false; this.isUploaded = true; };
    this.uploader.onCompleteItem = (item: any, response: any, status: any, headers: any) => {
      // this.router.navigateByUrl('/table', { skipLocationChange: true }).then(() => {
      //   this.router.navigate(['table'], {
      //     queryParams: {fileName: this.lastFileName, exist: this.valid}
      //   });
      // });
      this.downloadService.getMapTable('').subscribe(data => {
        this.dataSource = data;
        console.log(TableComponent.filename);
        console.log(data);
      });
      alert('File uploaded successfully');
    };

    this.uploader.onSuccessItem = (item, response, status, headers) => {
      // this.valid = JSON.parse(response).validation.isValid[0] !== 'false';
      // console.log(JSON.parse(response).validation.isValid[0]);
      // this.notFoundRncList = JSON.parse(response).validation.rnc;
      // if (!this.valid) {
      //   this.openDialog(this.notFoundRncList);
      // }

    };

    this.form = this.fb.group({
      search: new FormControl('')
    });

    return this.downloadService. getMapTable(TableComponent.filename).subscribe(data => {
      // this.dataSource = data.slice(1, data.length+1);
      this.dataSource = data;
      // this.headers = data[0];

      // this.names[0] = [];
    });

  }

  perform() {
    this.router.navigate(['table-download']);
  }

  showWarning() {
    // this.snackBar.open('RNC ' + this.notFoundRncList + ' not defined! Do you want do define it now?', 'Create rnc', {
    //   // duration: 10000,
    //   verticalPosition: 'bottom',
    //   horizontalPosition: 'left',
    //   panelClass: [this.classColor],
    //   data: 'asdasd'
    // });
    //
    // this.snackBar._openedSnackBarRef.onAction().subscribe(data => {
    //   this.openDialog();
    // });
  }

  scroll() {
    this.MyProp.nativeElement.scrollIntoView({ behavior: 'smooth', block: 'start' });
  }

  changeBoolean() {
    this.valid = !this.valid;
  }

  ngOnDestroy() {
    if (this.mySubscription) {
      this.mySubscription.unsubscribe();
    }
  }

  downloadFile() {
    return this.downloadService.getFile().subscribe(data => {
      this.saveFile(data, data.headers.get('content-disposition'));
    }, error => {
      console.log('Error during download file');
      console.log(error);
    }), () => console.log('OK');
  }

  saveFile(data: Blob, filename: string) {
    const name = filename.split('"');
    const blob = new Blob([data], { type: 'text/csv' });
    const a = document.createElement('a');
    a.href = window.URL.createObjectURL(blob);
    a.download = name[1];
    a.click();
  }

  fileProgress(fileInput: any) {
    this.fileData = fileInput.target.files[0] as File;
    this.preview();
  }

  browse() {
    let elem = document.getElementById('selectedFile');
    if(elem) {
      elem.click();
    }
    
  }

  preview() {
    // Show preview
    const mimeType = this.fileData.type;
    if (mimeType.match('/image\/*/') == null) {
      return;
    }

    const reader = new FileReader();
    reader.readAsDataURL(this.fileData);
    reader.onload = (event) => {
      this.previewUrl = reader.result;
    };
  }

  showResult() {
    /* this.dialog.open(ShowResultComponent, {
      width: '700px',
      height: '400px',
      data: TableComponent.objects
    }); */

    if (TableComponent.objects) {
      this.router.navigate(['compare']);
    }
  }
}
