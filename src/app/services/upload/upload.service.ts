import { Injectable } from '@angular/core';
import {
  HttpClient,
  HttpRequest,
  HttpEventType,
  HttpResponse,
  HttpHeaders,
} from '@angular/common/http'
import { Observable } from 'rxjs';
import * as myGlobals from '../../services/globals';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class UploadService {

  headers: HttpHeaders  = new HttpHeaders({
    'Accept': 'application/json'
  });

  public API = myGlobals.API + 'v1/rnc/upload';

  constructor(private http: HttpClient) { }

  upload(formData): Observable<any> {
    return this.http.post(this.API, formData, {headers: this.headers, observe: 'response'});
  }

}
