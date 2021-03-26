import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';
import { delay, map } from 'rxjs/operators';
import * as myGlobals from '../../services/globals';

@Injectable({
  providedIn: 'root'
})
export class DownloadService {

  public API = myGlobals.API + 'v1/rnc/';

  headers: HttpHeaders  = new HttpHeaders({
    'Access-Control-Expose-Headers': 'Custom-Header, X-Auth-Token, Content-Type'
  });

  constructor(private http: HttpClient, private cookieService: CookieService) {
  }

  getFile(fileName: string): Observable<any> {
    return this.http.get(this.API + 'download/' + fileName, { headers: this.headers, responseType: 'blob' as 'json', observe: 'response' });
  }

  performIubUtranCell(): Observable<any> {
    return this.http.get(this.API + 'perform/iub-utran-cell', { responseType: 'blob', observe: 'response' });
  }

  performExternalNeighbours(): Observable<any> {
    return this.http.get(this.API + 'perform/external-neighbours', { responseType: 'blob', observe: 'response' });
  }

  getTable(filename: string): Observable<any> {
    return this.http.get(this.API + 'file/' + filename);
  }

  getMapTable(filename: string): Observable<any> {
    return this.http.get(this.API + "fileMap/" + filename);
  }

  getFileOfChanges(filename: string): Observable<any> {
    return this.http.get(this.API + "get-file-of-changes/" + filename, {headers: this.headers, observe: 'response'});
  }

  reCreateFileOfChanges(): Observable<any> {

    return this.http.get(this.API + "recreate-file-of-changes/", {headers: this.headers, observe: 'response'});
  }

  reCreateValidatedFileOfChanges(): Observable<any> {
    return this.http.get(this.API + "recreate-validated-file-of-changes/", {headers: this.headers, observe: 'response'});
  }

  reCreateAvailableFileOfChanges(): Observable<any> {
    return this.http.get(this.API + "recreate-available-file-of-changes/", {headers: this.headers, observe: 'response'});
  }

  validateRnc(file: any): Observable<any> {
    return this.http.post(this.API + "validate-file-of-changes", file, {headers: this.headers, observe: 'response'});
  }

  checkAvailableparams(file: any): Observable<any> {
    return this.http.post(this.API + "check-available-params", file, {headers: this.headers, observe: 'response'});
  }

  inputChanges(fileOfChanges: string): Promise<object> {
    return this.http.get(this.API + 'modifyFile/' + fileOfChanges).toPromise();
  }

  getAlarmFile(file: string) {
    return this.http.get(this.API + 'get-alarm-file/' + file, {headers: this.headers, observe: 'response'});
  }

  downloadAlarmFile(fileName): Observable<any> {
    return this.http.get(this.API + 'download-alarm-file/' + fileName, { headers: this.headers, responseType: 'blob', observe: 'response' });
  }
}
