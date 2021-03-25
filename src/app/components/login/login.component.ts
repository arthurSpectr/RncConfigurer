import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {LoginService} from '../../services/login/login.service';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {Observable} from 'rxjs';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router, private loginService: LoginService, private httpClient: HttpClient, private cookieService: CookieService) { }
  username: string = '';
  password: string = '';
  showSpinner = false;

  ngOnInit() {
  }
  login(): void {

    this.loginService.authenticate(this.username, this.password).subscribe(data => {
      sessionStorage.setItem('username', this.username);


      // this.loginService.getToken().subscribe(data => {
      //   sessionStorage.setItem("Token", data['token']);
      // }, error => {
      //   console.log(error);
      // });

      // console.log('request was succesfully executed');

      if (this.loginService.isUserLoggedIn()) {
        this.router.navigate(['stepper']);
      }
    }, error => {
      console.log(error);
    });


    // sessionStorage.setItem('username', this.username);
    // if (this.loginService.isUserLoggedIn()) {
    //   // this.router.navigate(['table'], {
    //   //   queryParams: {fileName: 'RncMaximoTable.csv'}
    //   // });
    //   this.router.navigate(['stepper']);
    // }
  }
}


