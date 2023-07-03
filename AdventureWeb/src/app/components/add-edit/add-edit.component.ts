import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
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

  changePosition = false;

  provinces!: any[];
  departments!: any[];
  shifts!: any[];

  minStartDate?: string;
  maxBirthDay: Date | null;
  minBirthDay: Date | null = new Date(1930, 0, 1);

  constructor(
    private _fb: FormBuilder,
    private apiService: ApiService,
    private _dialogRef: MatDialogRef<AddEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private _coreService: CoreService,
  ) {

    this.maxBirthDay = new Date(new Date().setFullYear(new Date().getFullYear() - 18));

    if (!data) {
      this.changePosition = true;
    } else {
      this.minStartDate = data.startDate;
    }

    this.empForm = this._fb.group({
      employeeId: new FormControl({value: '', disabled: true}),
      firstName: '',
      middleName: '',
      lastName: '',
      jobTitle: new FormControl({value: '', disabled: !this.changePosition}, Validators.required),
      departmentId: new FormControl({value: '', disabled: !this.changePosition}, Validators.required),
      department: '',
      startDate: new FormControl({value: '', disabled: !this.changePosition}, Validators.required),
      birthDay: new FormControl({value: '', max: this.maxBirthDay}, Validators.required),
      phoneNumber: '',
      emailAddressId: '',
      emailAddress: ['', [Validators.required, Validators.pattern('[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$')]],
      addressLine1: '',
      addressLine2: '',
      city: '',
      provinceId: '',
      province: '',
      postalCode: '',
      country: '',
      shiftID: new FormControl({value: '', disabled: !this.changePosition}, Validators.required),
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

  setChangePosition() {
    this.changePosition = !this.changePosition;

    if (this.changePosition) {
      this.empForm.get('jobTitle')?.enable();
      this.empForm.get('departmentId')?.enable();
      this.empForm.get('shiftID')?.enable();
      this.empForm.get('startDate')?.enable();
    } else {
      this.empForm.get('jobTitle')?.disable();
      this.empForm.get('departmentId')?.disable();
      this.empForm.get('shiftID')?.disable();
      this.empForm.get('startDate')?.disable();
    }

  }

  onFormSubmit() {
    if (this.empForm.valid) {
      if (this.data) {

        if (!this.changePosition) {
          this.empForm.value.departmentId = 0;
        } else {

          if (this.data.departmentId == this.empForm.value.departmentId) {
            this._coreService.openSnackBar('You need to select a different department');
            return;
          }

          if (this.data.jobTitle == this.empForm.value.jobTitle) {
            this._coreService.openSnackBar('You need to set a different job title');
            return;
          }

          if (this.data.startDate == this.empForm.value.startDate) {
            this._coreService.openSnackBar('You need to select a different start date');
            return;
          }

          if (this.empForm.value.startDate <= this.data.startDate) {
            this._coreService.openSnackBar('The start date must be greater than the current');
            return;
          }

        }

        this.apiService
          .updateEmployee(this.data.employeeId, this.empForm.value)
          .subscribe({
            next: (val: any) => {
              this._coreService.openSnackBar('Employee updated!');
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
