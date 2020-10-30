import {Component, Input, OnInit} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";


@Component({
  selector: 'app-grid',
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.scss']
})
export class GridComponent implements OnInit {

  @Input()
  headers = [];
  @Input()
  values = [];

  @Input()
  values2 = [];

  // headers = [
  //   'Rehome Order', 'BSC', 'Site', 'Cell', 'LON', 'LAT', 'LAC', 'CI', 'New BSC', 'New LAC', 'New CI', 'New Ura', 'New_RBSID_1', 'New_RBSID_2'
  // ];

  items = ['14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27'];

  constructor() {
    console.log(this.values2);
  }

  ngOnInit(): void {
    console.log(this.values2);
  }

}
