import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IFileSourceDetails, FileSourceDetails } from 'app/shared/model/file-source-details.model';
import { FileSourceDetailsService } from './file-source-details.service';
import { IFileInfo } from 'app/shared/model/file-info.model';
import { FileInfoService } from 'app/entities/file-info/file-info.service';

@Component({
  selector: 'jhi-file-source-details-update',
  templateUrl: './file-source-details-update.component.html',
})
export class FileSourceDetailsUpdateComponent implements OnInit {
  isSaving = false;
  fileinfos: IFileInfo[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    apiKey: [],
    userName: [],
    password: [],
    protocol: [],
    url: [],
    createdAt: [],
    createdBy: [],
    fileInfo: [],
  });

  constructor(
    protected fileSourceDetailsService: FileSourceDetailsService,
    protected fileInfoService: FileInfoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fileSourceDetails }) => {
      if (!fileSourceDetails.id) {
        const today = moment().startOf('day');
        fileSourceDetails.createdAt = today;
      }

      this.updateForm(fileSourceDetails);

      this.fileInfoService.query().subscribe((res: HttpResponse<IFileInfo[]>) => (this.fileinfos = res.body || []));
    });
  }

  updateForm(fileSourceDetails: IFileSourceDetails): void {
    this.editForm.patchValue({
      id: fileSourceDetails.id,
      name: fileSourceDetails.name,
      apiKey: fileSourceDetails.apiKey,
      userName: fileSourceDetails.userName,
      password: fileSourceDetails.password,
      protocol: fileSourceDetails.protocol,
      url: fileSourceDetails.url,
      createdAt: fileSourceDetails.createdAt ? fileSourceDetails.createdAt.format(DATE_TIME_FORMAT) : null,
      createdBy: fileSourceDetails.createdBy,
      fileInfo: fileSourceDetails.fileInfo,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const fileSourceDetails = this.createFromForm();
    if (fileSourceDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.fileSourceDetailsService.update(fileSourceDetails));
    } else {
      this.subscribeToSaveResponse(this.fileSourceDetailsService.create(fileSourceDetails));
    }
  }

  private createFromForm(): IFileSourceDetails {
    return {
      ...new FileSourceDetails(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      apiKey: this.editForm.get(['apiKey'])!.value,
      userName: this.editForm.get(['userName'])!.value,
      password: this.editForm.get(['password'])!.value,
      protocol: this.editForm.get(['protocol'])!.value,
      url: this.editForm.get(['url'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      fileInfo: this.editForm.get(['fileInfo'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFileSourceDetails>>): void {
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

  trackById(index: number, item: IFileInfo): any {
    return item.id;
  }
}
