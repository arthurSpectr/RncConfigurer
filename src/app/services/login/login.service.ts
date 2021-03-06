import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import {error} from "@angular/compiler/src/util";
import * as myGlobals from '../globals';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private httpClient: HttpClient, private router: Router, private cookieService: CookieService) { }

  form: FormData = new FormData();

  headers = new HttpHeaders();

  authenticate(username: string, password: string | Blob) {
    this.form.delete('username');
    this.form.delete('password');
    this.form.append('username', username);
    this.form.append('password', password);

    this.headers.set('Content-Type', 'application/x-www-form-urlencoded');
    // this.headers.append('Cookie', this.cookieService.get('JSESSIONID'));
    // this.headers.append("Authorization", 'Basic ' + btoa(username + ':' + password));

    return this.httpClient.post(myGlobals.API + 'login', this.form, {withCredentials: true, headers: this.headers}).pipe(
      map(
        userData => {
          sessionStorage.setItem('username', username);
          // let tokenStr = 'Bearer '+userData['token'];
          // sessionStorage.setItem('token', tokenStr);
          return userData;
        }
      )
    );
  }

  getToken() {
    return this.httpClient.get(myGlobals.API + 'v1/rnc/token');
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('username')
    return !(user === null)
  }

  logOut() {
   this.httpClient.post(myGlobals.API + 'logout', '').subscribe(data => {
      console.log('succesfully logout');
      sessionStorage.clear();
      this.router.navigate(['login']);
    }, error => {
     console.log('logout error');
     console.log(error);
   });

  }

}
