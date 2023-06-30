import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {CoreService} from '../../core/core.service';
import {ApiService} from '../../api.service';

@Component({
  selector: 'app-emp-add-edit',
  templateUrl: './add-edit.component.html',
  styleUrls: ['./add-edit.component.scss'],
})
export class AddEditComponent implements OnInit {
  empForm: FormGroup;

  education: string[] = [
    'Matric',
    'Diploma',
    'Intermediate',
    'Graduate',
    'Post Graduate',
  ];

  provinces!: any[];
  departments!: any[];
  shifts!: any[];

  constructor(
    private _fb: FormBuilder,
    private apiService: ApiService,
    private _dialogRef: MatDialogRef<AddEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private _coreService: CoreService
  ) {
    this.empForm = this._fb.group({
      employeeId: '',
      firstName: '',
      middleName: '',
      lastName: '',
      jobTitle: '',
      departmentId: '',
      department: '',
      startDate: '',
      birthDay: '',
      phoneNumber: '',
      emailAddressId: '',
      emailAddress: '',
      addressLine1: '',
      addressLine2: '',
      city: '',
      provinceId: '',
      province: '',
      postalCode: '',
      country: '',
      shiftID: '',
    });

    this.getProvincesList();
    this.getDepartmentsList();
    this.getShiftsList();
  }

  ngOnInit(): void {
    this.empForm.patchValue(this.data);
  }

  getProvincesList() {
    this.apiService
      .getProvinces()
      .subscribe({
        next: (res) => {
          this.provinces = res;
        },
        error: console.log,
      });
  }

  getDepartmentsList() {
    this.apiService
      .getDepartments()
      .subscribe({
        next: (res) => {
          this.departments = res;
        },
        error: console.log,
      });
  }

  getShiftsList() {
    this.apiService
      .getShifts()
      .subscribe({
        next: (res) => {
          this.shifts = res;
        },
        error: console.log,
      });
  }

  onFormSubmit() {
    if (this.empForm.valid) {
      if (this.data) {
        this.apiService
          .updateEmployee(this.data.id, this.empForm.value)
          .subscribe({
            next: (val: any) => {
              this._coreService.openSnackBar('Employee detail updated!');
              this._dialogRef.close(true);
            },
            error: (err: any) => {
              console.error(err);
            },
          });
      } else {
        this.apiService.addEmployee(this.empForm.value).subscribe({
          next: (val: any) => {
            this._coreService.openSnackBar('Employee added successfully');
            this._dialogRef.close(true);
          },
          error: (err: any) => {
            console.error(err);
          },
        });
      }
    }
  }
}
