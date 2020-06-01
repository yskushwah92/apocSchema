import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IFileInfo, FileInfo } from 'app/shared/model/file-info.model';
import { FileInfoService } from './file-info.service';

@Component({
  selector: 'jhi-file-info-update',
  templateUrl: './file-info-update.component.html',
})
export class FileInfoUpdateComponent implements OnInit {
  isSaving = false;
  fileinfos: IFileInfo[] = [];

  editForm = this.fb.group({
    id: [],
    source: [],
    sourceDetails: [],
    senderMailId: [],
    filePath: [],
    fileName: [],
    reason: [],
    fileExtension: [],
    type: [],
    documentType: [],
    documentCategory: [],
    clientId: [],
    clientDetails: [],
    protocol: [],
    createdAt: [],
    createdBy: [],
    fileInfo: [],
  });

  constructor(protected fileInfoService: FileInfoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fileInfo }) => {
      if (!fileInfo.id) {
        const today = moment().startOf('day');
        fileInfo.createdAt = today;
      }

      this.updateForm(fileInfo);

      this.fileInfoService.query().subscribe((res: HttpResponse<IFileInfo[]>) => (this.fileinfos = res.body || []));
    });
  }

  updateForm(fileInfo: IFileInfo): void {
    this.editForm.patchValue({
      id: fileInfo.id,
      source: fileInfo.source,
      sourceDetails: fileInfo.sourceDetails,
      senderMailId: fileInfo.senderMailId,
      filePath: fileInfo.filePath,
      fileName: fileInfo.fileName,
      reason: fileInfo.reason,
      fileExtension: fileInfo.fileExtension,
      type: fileInfo.type,
      documentType: fileInfo.documentType,
      documentCategory: fileInfo.documentCategory,
      clientId: fileInfo.clientId,
      clientDetails: fileInfo.clientDetails,
      protocol: fileInfo.protocol,
      createdAt: fileInfo.createdAt ? fileInfo.createdAt.format(DATE_TIME_FORMAT) : null,
      createdBy: fileInfo.createdBy,
      fileInfo: fileInfo.fileInfo,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const fileInfo = this.createFromForm();
    if (fileInfo.id !== undefined) {
      this.subscribeToSaveResponse(this.fileInfoService.update(fileInfo));
    } else {
      this.subscribeToSaveResponse(this.fileInfoService.create(fileInfo));
    }
  }

  private createFromForm(): IFileInfo {
    return {
      ...new FileInfo(),
      id: this.editForm.get(['id'])!.value,
      source: this.editForm.get(['source'])!.value,
      sourceDetails: this.editForm.get(['sourceDetails'])!.value,
      senderMailId: this.editForm.get(['senderMailId'])!.value,
      filePath: this.editForm.get(['filePath'])!.value,
      fileName: this.editForm.get(['fileName'])!.value,
      reason: this.editForm.get(['reason'])!.value,
      fileExtension: this.editForm.get(['fileExtension'])!.value,
      type: this.editForm.get(['type'])!.value,
      documentType: this.editForm.get(['documentType'])!.value,
      documentCategory: this.editForm.get(['documentCategory'])!.value,
      clientId: this.editForm.get(['clientId'])!.value,
      clientDetails: this.editForm.get(['clientDetails'])!.value,
      protocol: this.editForm.get(['protocol'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      fileInfo: this.editForm.get(['fileInfo'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFileInfo>>): void {
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
