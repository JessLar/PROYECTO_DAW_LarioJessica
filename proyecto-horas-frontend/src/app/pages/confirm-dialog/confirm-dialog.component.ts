import { Component } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-confirm-dialog',
  template: `
    <h1 mat-dialog-title>Confirmar</h1>
    <div mat-dialog-content>
      <p>¿Está seguro de que desea eliminar este registro?</p>
    </div>
    <div mat-dialog-actions>
      <button mat-button (click)="onCancelClick()">Cancelar</button>
      <button mat-button color="primary" (click)="onConfirmClick()">Aceptar</button>
    </div>
  `,
})
export class ConfirmDialogComponent  {
  constructor(
    public dialogRef: MatDialogRef<ConfirmDialogComponent>,
  ) {}

  onCancelClick(): void {
    this.dialogRef.close(false);
  }

  onConfirmClick(): void {
    this.dialogRef.close(true);
  }
}
