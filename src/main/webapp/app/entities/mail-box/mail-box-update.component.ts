import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IMailBox, MailBox } from 'app/shared/model/mail-box.model';
import { MailBoxService } from './mail-box.service';

@Component({
  selector: 'jhi-mail-box-update',
  templateUrl: './mail-box-update.component.html',
})
export class MailBoxUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [],
    server: [],
    address: [],
    displayName: [],
    createdAt: [],
    createdBy: [],
  });

  constructor(protected mailBoxService: MailBoxService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ mailBox }) => {
      if (!mailBox.id) {
        const today = moment().startOf('day');
        mailBox.createdAt = today;
      }

      this.updateForm(mailBox);
    });
  }

  updateForm(mailBox: IMailBox): void {
    this.editForm.patchValue({
      id: mailBox.id,
      name: mailBox.name,
      server: mailBox.server,
      address: mailBox.address,
      displayName: mailBox.displayName,
      createdAt: mailBox.createdAt ? mailBox.createdAt.format(DATE_TIME_FORMAT) : null,
      createdBy: mailBox.createdBy,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const mailBox = this.createFromForm();
    if (mailBox.id !== undefined) {
      this.subscribeToSaveResponse(this.mailBoxService.update(mailBox));
    } else {
      this.subscribeToSaveResponse(this.mailBoxService.create(mailBox));
    }
  }

  private createFromForm(): IMailBox {
    return {
      ...new MailBox(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      server: this.editForm.get(['server'])!.value,
      address: this.editForm.get(['address'])!.value,
      displayName: this.editForm.get(['displayName'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMailBox>>): void {
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
}
