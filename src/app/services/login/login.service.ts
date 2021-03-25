import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { error } from "@angular/compiler/src/util";
import * as myGlobals from '../globals';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private httpClient: HttpClient, private router: Router, private cookieService: CookieService) { }

  form: FormData = new FormData();

  headers = new HttpHeaders({'Access-Control-Expose-Headers': 'Custom-Header, X-Auth-Token, Content-Type'});

  authenticate(username: string, password: string | Blob) {
    this.cookieService.deleteAll();
    sessionStorage.clear();


    this.form.delete('username');
    this.form.delete('password');
    this.form.append('username', username);
    this.form.append('password', password);

    // this.headers.set('Content-Type', 'application/x-www-form-urlencoded');
    this.headers.set('Content-Type', 'multipart/form-data');
    // this.headers.append('Cookie', this.cookieService.get('JSESSIONID'));
    // this.headers.append("Authorization", 'Basic ' + btoa(username + ':' + password));

    // return this.httpClient.post(myGlobals.API_LOGIN + 'login/user', this.form, { headers:  new HttpHeaders({'X-Auth-Token': ''}), withCredentials: true, observe: 'response' });
    return this.httpClient.post(myGlobals.API + 'login', this.form, {headers: this.headers, observe: 'response'});
  }

  getToken() {
    return this.httpClient.get(myGlobals.API + 'v1/rnc/token');
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('username')
    return !(user === null)
  }

  logOut() {
    console.log('in logout request X-Auth-Token - ', this.cookieService.get('X-Auth-Token'));


    this.httpClient.post(myGlobals.API_LOGIN + 'full/logout', {headers: this.headers}).subscribe(data => {
      console.log("during logout  ", data);


      this.httpClient.post(myGlobals.API + 'logout', {headers: this.headers, observe: 'response'}).subscribe(data => {
        this.cookieService.deleteAll();
        console.log('succesfully logout');
        sessionStorage.clear();
        this.router.navigate(['login']);
      }, error => {
        this.cookieService.deleteAll();
        sessionStorage.clear();
        console.log('logout error');
        console.log(error);
        this.router.navigate(['login']);
      });

    }, error => {
      console.log('error during logout');
    });

    

  }

}
