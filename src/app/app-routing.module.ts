import { NgModule } from '@angular/core';
import { Routes, RouterModule, ExtraOptions } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { LoginGuardService } from './services/security/login-guard.service';
import { StepperComponent } from './components/stepper/stepper.component';
import { GridComponent } from "./components/grid/grid.component";
import { FileViewerComponent } from './components/fileviewer/file-viewer.component';

const routerOptions: ExtraOptions = {
  scrollPositionRestoration: 'enabled',
  anchorScrolling: 'enabled',
  scrollOffset: [0, 64],
};
const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'stepper', component: StepperComponent, canActivate: [LoginGuardService]},
  {path: 'grid', component: GridComponent, canActivate: [LoginGuardService]},
  {path: 'fileViewer', component: FileViewerComponent, canActivate: [LoginGuardService]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, routerOptions)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
