export class RncModification {

    headers: Array<string> = [];
    values: Array<RowOfChanges> = [];

}

export class RowOfChanges {

    cells: Array<Cell> = [];

}

export class Cell {
    key: string;
    defaultValue: string;
    valid: boolean;
    possibleValues: Array<Cell>;
}