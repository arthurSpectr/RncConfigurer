import { Component, OnInit } from '@angular/core';
import {LoginService} from '../../services/login/login.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(private loginService: LoginService, private router: Router) {
    window.onload = () => {
      if (this.loginService.isUserLoggedIn()) {
        // this.router.navigate(['table'], {
        //   queryParams: {fileName: 'RncMaximoTable.csv'}
        // });
        this.router.navigate(['stepper']);
      } else {
        this.router.navigate(['login']);
      }
    };
  }

  ngOnInit() {
  }

  login(): void {
    this.router.navigate(['login']);
  }

  logOut(): void {
    this.loginService.logOut();
  }
}
