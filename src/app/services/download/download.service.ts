import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';
import {Rnc} from '../../model/model.rnc';
import {map} from 'rxjs/operators';
import {FileOfChanges} from "../../model/FileOfChanges";

@Injectable({
  providedIn: 'root'
})
export class DownloadService {

  public API = 'http://localhost:8089/api/v1/rnc/';

  headers = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      JSESSIONID: this.cookieService.get('JSESSIONID')
    })
  };

  constructor(private http: HttpClient, private cookieService: CookieService) {
  }

  getFile(): Observable<any> {
    return this.http.get(this.API + 'downloadFile/RncMaximoTableSample.csv', {responseType: 'blob' as 'json', observe: 'response'});
  }

  getFiles(): Observable<any> {
    return this.http.get(this.API + 'download/files', {responseType: 'blob', observe: 'response'});
  }

  getTable(filename: string): Observable<any> {
    return this.http.get(this.API + 'file/' + filename);
  }

  getMapTable(filename: string): Observable<any> {
    return this.http.get(this.API + "fileMap/" + filename);
  }

  getFileOfChanges(filename: string): Observable<any> {
    return this.http.get(this.API + "get-file-of-changes/" + filename);
  }

  validateRnc() {
    return this.http.get(this.API + "validate-file-of-changes");
  }

  inputChanges(fileOfChanges: string): Promise<object> {
    return this.http.get(this.API + 'modifyFile/' + fileOfChanges).toPromise();
  }
}
