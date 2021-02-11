import { Component } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatMenuModule } from '@angular/material/menu';
import {LoginService} from './services/login/login.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Rnc-ui';

  constructor(private login: LoginService, private router: Router) {
    window.onload = () => {
      if (this.login.isUserLoggedIn()) {
        // this.router.navigate(['table'], {
        //   queryParams: {fileName: 'RncMaximoTable.csv'}
        // });
        this.router.navigate(['stepper']);
      } else {
        this.router.navigate(['login']);
      }
    };
  }

}

