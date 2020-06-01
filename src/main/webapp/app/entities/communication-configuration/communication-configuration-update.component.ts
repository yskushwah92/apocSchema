import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICommunicationConfiguration, CommunicationConfiguration } from 'app/shared/model/communication-configuration.model';
import { CommunicationConfigurationService } from './communication-configuration.service';
import { IMailBox } from 'app/shared/model/mail-box.model';
import { MailBoxService } from 'app/entities/mail-box/mail-box.service';

@Component({
  selector: 'jhi-communication-configuration-update',
  templateUrl: './communication-configuration-update.component.html',
})
export class CommunicationConfigurationUpdateComponent implements OnInit {
  isSaving = false;
  mailboxes: IMailBox[] = [];

  editForm = this.fb.group({
    id: [],
    priority: [],
    retries: [],
    mode: [],
    name: [],
    createdAt: [],
    createdBy: [],
    mailBox: [],
  });

  constructor(
    protected communicationConfigurationService: CommunicationConfigurationService,
    protected mailBoxService: MailBoxService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ communicationConfiguration }) => {
      if (!communicationConfiguration.id) {
        const today = moment().startOf('day');
        communicationConfiguration.createdAt = today;
      }

      this.updateForm(communicationConfiguration);

      this.mailBoxService
        .query({ filter: 'communicationconfiguration-is-null' })
        .pipe(
          map((res: HttpResponse<IMailBox[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IMailBox[]) => {
          if (!communicationConfiguration.mailBox || !communicationConfiguration.mailBox.id) {
            this.mailboxes = resBody;
          } else {
            this.mailBoxService
              .find(communicationConfiguration.mailBox.id)
              .pipe(
                map((subRes: HttpResponse<IMailBox>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IMailBox[]) => (this.mailboxes = concatRes));
          }
        });
    });
  }

  updateForm(communicationConfiguration: ICommunicationConfiguration): void {
    this.editForm.patchValue({
      id: communicationConfiguration.id,
      priority: communicationConfiguration.priority,
      retries: communicationConfiguration.retries,
      mode: communicationConfiguration.mode,
      name: communicationConfiguration.name,
      createdAt: communicationConfiguration.createdAt ? communicationConfiguration.createdAt.format(DATE_TIME_FORMAT) : null,
      createdBy: communicationConfiguration.createdBy,
      mailBox: communicationConfiguration.mailBox,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const communicationConfiguration = this.createFromForm();
    if (communicationConfiguration.id !== undefined) {
      this.subscribeToSaveResponse(this.communicationConfigurationService.update(communicationConfiguration));
    } else {
      this.subscribeToSaveResponse(this.communicationConfigurationService.create(communicationConfiguration));
    }
  }

  private createFromForm(): ICommunicationConfiguration {
    return {
      ...new CommunicationConfiguration(),
      id: this.editForm.get(['id'])!.value,
      priority: this.editForm.get(['priority'])!.value,
      retries: this.editForm.get(['retries'])!.value,
      mode: this.editForm.get(['mode'])!.value,
      name: this.editForm.get(['name'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      mailBox: this.editForm.get(['mailBox'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICommunicationConfiguration>>): void {
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

  trackById(index: number, item: IMailBox): any {
    return item.id;
  }
}
