import { Component, OnInit } from '@angular/core';
import { DownloadService } from '../../services/download/download.service';
import {Router, ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-file-viewer',
  templateUrl: './file-viewer.component.html',
  styleUrls: ['./file-viewer.component.scss']
})
export class FileViewerComponent implements OnInit {

  alarmFile: Array<any>;
  fileName;

  constructor(public service: DownloadService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    

    this.route.queryParams.subscribe(params => {
      this.fileName = params.file;
      console.log(params);
    })

    this.service.getAlarmFile(this.fileName).subscribe(data => {
      this.alarmFile = data as Array<any>;


      console.log("FileViewerComponent after getAlarmFile request  ", data)
    })
  }

  downloadFile() {
    this.service.downloadAlarmFile(this.fileName).subscribe(data => {
      this.saveFile(data.body, data.headers.get('content-disposition'));
    }, error => {
      console.log('Error during download file');
      console.log(error);
    }, () => console.log('OK'));
  }

  saveFile(data: Blob, filename: string) {

    const name = filename.split('"');
    const blob = new Blob([data], { type: 'application/zip' });
    const a = document.createElement('a');
    a.href = window.URL.createObjectURL(blob);
    a.download = name[1];
    a.click();
  }

  back() {
    this.router.navigate(['stepper']);
  }
}
