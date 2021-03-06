import { Component, Input, OnInit, Output, EventEmitter  } from '@angular/core';
import { MatTableDataSource } from "@angular/material/table";
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { MatSelect } from '@angular/material/select';


@Component({
  selector: 'app-grid',
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.scss']
})
export class GridComponent implements OnInit {

  @Output() someEvent = new EventEmitter();

  @Input()
  headers = [];
  @Input()
  values = [];

  @Input()
  values2 = [];

  @Input()
  availableValues = [];

  // headers = [
  //   'Rehome Order', 'BSC', 'Site', 'Cell', 'LON', 'LAT', 'LAC', 'CI', 'New BSC', 'New LAC', 'New CI', 'New Ura', 'New_RBSID_1', 'New_RBSID_2'
  // ];

  items = ['14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27'];

  selected = 'option';

  constructor() {
  }

  ngOnInit(): void {

    this.values2 = [
      [true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true],
      [true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true],
      [true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true],
      [true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true],
      [true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true],
      [true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true],
      [true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true],
      [true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true],
      [true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true],
      [true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true],
      [true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true],
      [true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true],
    ]
  }

  trackByFn(index: any, item: any) {
    return index;
  }

  checkIfAvailables(event, number, line, cell) {
    let target: MatSelect = event.source;

    let value = event.value;

    console.log("event value - ", value, "   number - ", number);

    if (value != number) {
      let style1: CSSStyleDeclaration = target._elementRef.nativeElement.style;

      style1.backgroundColor = 'transparent';

    } else {
      let style1: CSSStyleDeclaration = target._elementRef.nativeElement.style;

      if (this.values2[line][cell] == false) {
        style1.backgroundColor = '#ffcccb';
      }
    }

    let allValues = {
      'values': this.values,
      'availablaValues': this.availableValues
    };

    this.someEvent.next(allValues);
    
  }

  showStyle(event) {
    console.log(event);
  }

  isArray(line, cell): boolean {


    if (this.availableValues.length == 0) return false;

    if (this.availableValues[line] == null) return false;

    if (this.availableValues[line][cell] == null) return false;

    if (Array.isArray(this.availableValues[line][cell])) return true;
  }

  styleObject(index1: number, index2: number) {

    if(this.values2.length == 0) return {};
    if(this.values2[index1] == null) return {};

    let isValid = this.values2[index1][index2];
    if(isValid == null) return {};

    // console.log("in styleObject - ", isValid, "and  isValid == false && isValid != null - ",  isValid === false && isValid != null);

    if(  isValid === false ) {
      return {color: 'black', backgroundColor: '#ffcccb'};
    }

  }
}
