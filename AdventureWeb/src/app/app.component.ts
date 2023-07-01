import {Component, OnInit, ViewChild} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {AddEditComponent} from './components/add-edit/add-edit.component';
import {ApiService} from './api.service';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {CoreService} from './core/core.service';
import {DeleteConfirmationComponent} from "./components/delete-confirmation/delete-confirmation.component";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  displayedColumns: string[] = [
    'name',
    'jobTitle',
    'department',
    'startDate',
    'birthDay',
    'phoneNumber',
    'emailAddress',
    'address',
    'action',
  ];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private dialog: MatDialog,
    private apiService: ApiService,
    private coreService: CoreService
  ) {
  }

  ngOnInit(): void {
    this.getEmployeeList();
  }

  getEmployeeList() {
    this.apiService
      .getAllEmployees()
      .subscribe({
        next: (res) => {
          this.dataSource = new MatTableDataSource(res);
          this.dataSource.sort = this.sort;
          this.dataSource.paginator = this.paginator;
        },
        error: console.log,
      });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  openAddEditEmpForm() {
    const dialogRef = this.dialog.open(AddEditComponent);
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getEmployeeList();
        }
      },
    });
  }

  deleteEmployee(id: number, firstName: string, lastName: string) {

    const dialogRef = this.dialog.open(DeleteConfirmationComponent, {
      width: '300px',
      data: {employeeName: firstName + ' ' + lastName},
    });

    dialogRef
      .afterClosed()
      .subscribe(result => {
        if (result) {
          this.apiService
            .deleteEmployee(id)
            .subscribe({
              next: (res) => {
                this.coreService.openSnackBar('Employee deleted!', 'done');
                this.getEmployeeList();
              },
              error: console.log,
            });
        }
      });

  }

  openEditForm(data: any) {
    const dialogRef = this.dialog.open(AddEditComponent, {
      data,
    });

    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.getEmployeeList();
        }
      },
    });
  }

  showSalesReport(id: number) {

  }

}
