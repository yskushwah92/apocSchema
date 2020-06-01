import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IAssignment, Assignment } from 'app/shared/model/assignment.model';
import { AssignmentService } from './assignment.service';
import { IInvoice } from 'app/shared/model/invoice.model';
import { InvoiceService } from 'app/entities/invoice/invoice.service';

@Component({
  selector: 'jhi-assignment-update',
  templateUrl: './assignment-update.component.html',
})
export class AssignmentUpdateComponent implements OnInit {
  isSaving = false;
  invoices: IInvoice[] = [];

  editForm = this.fb.group({
    id: [],
    assignmentDate: [],
    assignedTo: [],
    assignedBy: [],
    setSLA: [],
    assignmentStatus: [],
    percentageCompleted: [],
    assignmentMode: [],
    createdAt: [],
    createdBy: [],
    invoice: [],
  });

  constructor(
    protected assignmentService: AssignmentService,
    protected invoiceService: InvoiceService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ assignment }) => {
      if (!assignment.id) {
        const today = moment().startOf('day');
        assignment.assignmentDate = today;
        assignment.setSLA = today;
        assignment.createdAt = today;
      }

      this.updateForm(assignment);

      this.invoiceService.query().subscribe((res: HttpResponse<IInvoice[]>) => (this.invoices = res.body || []));
    });
  }

  updateForm(assignment: IAssignment): void {
    this.editForm.patchValue({
      id: assignment.id,
      assignmentDate: assignment.assignmentDate ? assignment.assignmentDate.format(DATE_TIME_FORMAT) : null,
      assignedTo: assignment.assignedTo,
      assignedBy: assignment.assignedBy,
      setSLA: assignment.setSLA ? assignment.setSLA.format(DATE_TIME_FORMAT) : null,
      assignmentStatus: assignment.assignmentStatus,
      percentageCompleted: assignment.percentageCompleted,
      assignmentMode: assignment.assignmentMode,
      createdAt: assignment.createdAt ? assignment.createdAt.format(DATE_TIME_FORMAT) : null,
      createdBy: assignment.createdBy,
      invoice: assignment.invoice,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const assignment = this.createFromForm();
    if (assignment.id !== undefined) {
      this.subscribeToSaveResponse(this.assignmentService.update(assignment));
    } else {
      this.subscribeToSaveResponse(this.assignmentService.create(assignment));
    }
  }

  private createFromForm(): IAssignment {
    return {
      ...new Assignment(),
      id: this.editForm.get(['id'])!.value,
      assignmentDate: this.editForm.get(['assignmentDate'])!.value
        ? moment(this.editForm.get(['assignmentDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      assignedTo: this.editForm.get(['assignedTo'])!.value,
      assignedBy: this.editForm.get(['assignedBy'])!.value,
      setSLA: this.editForm.get(['setSLA'])!.value ? moment(this.editForm.get(['setSLA'])!.value, DATE_TIME_FORMAT) : undefined,
      assignmentStatus: this.editForm.get(['assignmentStatus'])!.value,
      percentageCompleted: this.editForm.get(['percentageCompleted'])!.value,
      assignmentMode: this.editForm.get(['assignmentMode'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      invoice: this.editForm.get(['invoice'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAssignment>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IInvoice): any {
    return item.id;
  }
}
