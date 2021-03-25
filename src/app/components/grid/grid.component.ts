import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { MatTableDataSource } from "@angular/material/table";
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { MatSelect } from '@angular/material/select';
import {RncModification} from 'src/app/model/rnc-modification';


@Component({
  selector: 'app-grid',
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.scss']
})
export class GridComponent implements OnInit {

  @Output() validateValuesEvent = new EventEmitter();

  @Input()
  rncModification: RncModification;

  constructor() {
  }

  ngOnInit(): void {

    // for (let i = 0; i < 12; i++) {
    //   let rowArray = [];
    //   for (let q = 0; q < 21; q++) {
    //     rowArray.push(true);
    //   }
    //   this.validatedValues.push(rowArray);
    // }

    // console.log(this.validatedValues);
  }

  trackByFn(index: any, item: any) {
    return index;
  }

  checkIfCurrentValueAvailable(event, line, cell) {
    let target: MatSelect = event.source;

    let selectedValue = event.value;
    let unAvailableValue = this.rncModification.values[line].cells[cell].possibleValues[0].defaultValue;
    console.log("selectedValue value - ", selectedValue, "   unAvailableValue - ", unAvailableValue);
    console.log('is selectedValue != unAvailableValue - ', selectedValue != unAvailableValue)

    if (selectedValue != unAvailableValue) {
      let unavailableCellValueStyle: CSSStyleDeclaration = target._elementRef.nativeElement.style;
      unavailableCellValueStyle.backgroundColor = 'transparent';

      this.rncModification.values[line].cells[cell].valid = true;
    } else {
      let unavailableCellValueStyle: CSSStyleDeclaration = target._elementRef.nativeElement.style;
      unavailableCellValueStyle.backgroundColor = '#ffcccb';
      this.rncModification.values[line].cells[cell].valid = false;
    }

    let validatedValues = {
      'validatedValues': this.rncModification.values
    };

    this.validateValuesEvent.next(validatedValues);

  }

  // isArray(line, cell): boolean {


  //   if (this.availableValues.length == 0) return false;

  //   if (this.availableValues[line] == null) return false;

  //   if (this.availableValues[line][cell] == null) return false;

  //   if (Array.isArray(this.availableValues[line][cell])) return true;
  // }

  // styleObject(index1: number, index2: number) {

  //   if (this.validatedValues.length == 0) return {};
  //   if (this.validatedValues[index1] == null) return {};

  //   let isValid = this.validatedValues[index1][index2];
  //   if (isValid == null) return {};

  //   // console.log("in styleObject - ", isValid, "and  isValid == false && isValid != null - ",  isValid === false && isValid != null);

  //   if (isValid === false) {
  //     return { color: 'black', backgroundColor: '#ffcccb' };
  //   }

  // }
}
