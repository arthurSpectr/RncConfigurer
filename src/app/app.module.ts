import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CookieService } from 'ngx-cookie-service';
import {MatDialogModule} from '@angular/material/dialog';
import {MatStepperModule} from '@angular/material/stepper';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { TableComponent } from './components/table/table-main/table.component';
import {LoginComponent} from './components/login/login.component';
import {FooterComponent} from './components/footer/footer.component';
import {HeaderComponent} from './components/header/header.component';
// import {TableWarningComponent} from './components/table/table-warning/table-warning.component';
// import {PerformComponent} from './components/perform/perform.component';
// import {TableDownloadComponent} from './components/table/table-download/table-download.component';
// import {CompareComponent} from './components/dialogs/compare/compare.component';
// import {ShowResultComponent} from './components/show-result/show-result.component';
import { FileUploadModule } from 'ng2-file-upload';
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatMenuModule} from '@angular/material/menu';
import {MatTableModule} from '@angular/material/table';
import {HttpClientModule} from '@angular/common/http';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { StepperComponent } from './components/stepper/stepper.component';
// import { TableCloneComponent } from './components/table/table-clone/table-clone.component';
import { GridComponent } from './components/grid/grid.component';
import {MatGridListModule} from '@angular/material/grid-list';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

@NgModule({
  declarations: [
    AppComponent,
    TableComponent,
    LoginComponent,
    HeaderComponent,
    FooterComponent,
    // TableWarningComponent,
    // PerformComponent,
    // TableDownloadComponent,
    // ShowResultComponent,
    // CompareComponent,
    StepperComponent,
    // TableCloneComponent,
    GridComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatProgressBarModule,
    MatStepperModule,
    FileUploadModule,
    MatCardModule,
    MatFormFieldModule,
    MatProgressSpinnerModule,
    MatToolbarModule,
    MatIconModule,
    MatMenuModule,
    MatTableModule,
    MatButtonModule,
    MatInputModule,
    MatSnackBarModule,
    MatGridListModule
  ],
  providers: [
    CookieService,
    // {
    // provide: HTTP_INTERCEPTORS, useClass: LoginIntercept, multi: true
    // }
  ],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }
