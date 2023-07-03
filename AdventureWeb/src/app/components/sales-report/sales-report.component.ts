import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {ConfirmationDialogData} from "../delete-confirmation/delete-confirmation.component";
import {FormControl} from "@angular/forms";
import {ApiService} from "../../api.service";
import {DatePipe} from '@angular/common'
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-sales-report',
  templateUrl: './sales-report.component.html',
  styleUrls: ['./sales-report.component.css']
})
export class SalesReportComponent {

  totalSales = 0.0;
  averageSales = 0.0;

  startDate: FormControl;
  endDate: FormControl;

  displayedColumns: string[] = [
    'orderDate',
    'statusName',
    'accountNumber',
    'address',
    'subtotal',
    'taxAmount',
    'total'
  ];
  dataSource!: MatTableDataSource<any>;

  constructor(
    private apiService: ApiService,
    public dialogRef: MatDialogRef<SalesReportComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ConfirmationDialogData,
    public datePipe: DatePipe,
  ) {

    const previousDate = this.getDateWithPreviousMonth(new Date());

    this.startDate = new FormControl(new Date(previousDate.getFullYear(), previousDate.getMonth(), 1));
    this.endDate = new FormControl(new Date(previousDate.getFullYear(), previousDate.getMonth() + 1, 0));

    this.refreshReport();

  }

  onNoClick(): void {
    this.dialogRef.close(false);
  }

  getDateWithPreviousMonth(date: Date): Date {
    const currentMonth = date.getMonth();
    const currentYear = date.getFullYear();

    if (currentMonth === 0) {
      const newYear = currentYear - 1;
      const newMonth = 11;

      return new Date(newYear, newMonth, date.getDate());
    } else {
      const newMonth = currentMonth - 1;
      return new Date(currentYear, newMonth, date.getDate());
    }
  }

  refreshReport(): void {

    this.totalSales = 0.0;
    this.averageSales = 0.0;

    const reportStartDate = this.datePipe.transform(this.startDate.value, 'yyyy-MM-dd');
    const reportEndDate = this.datePipe.transform(this.endDate.value, 'yyyy-MM-dd');

    this.apiService
      .getSalesByEmployee(this.data.employeeId, reportStartDate, reportEndDate)
      .subscribe({
        next: (res) => {
          this.dataSource = new MatTableDataSource(res);

          for (let i = 0; i < res.length; i++) {
            this.totalSales += res[i]['total'];
          }

          if (res.length > 0) {
            this.averageSales = this.totalSales / res.length;
          }

        },
        error: console.log,
      });
  }

}
