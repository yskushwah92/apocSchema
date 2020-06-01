import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IOCRExtractionEngineInfo, OCRExtractionEngineInfo } from 'app/shared/model/ocr-extraction-engine-info.model';
import { OCRExtractionEngineInfoService } from './ocr-extraction-engine-info.service';
import { IModelInfo } from 'app/shared/model/model-info.model';
import { ModelInfoService } from 'app/entities/model-info/model-info.service';

@Component({
  selector: 'jhi-ocr-extraction-engine-info-update',
  templateUrl: './ocr-extraction-engine-info-update.component.html',
})
export class OCRExtractionEngineInfoUpdateComponent implements OnInit {
  isSaving = false;
  modelinfos: IModelInfo[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    userName: [],
    password: [],
    apiKey: [],
    createdAt: [],
    createdBy: [],
    modelInfo: [],
  });

  constructor(
    protected oCRExtractionEngineInfoService: OCRExtractionEngineInfoService,
    protected modelInfoService: ModelInfoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ oCRExtractionEngineInfo }) => {
      if (!oCRExtractionEngineInfo.id) {
        const today = moment().startOf('day');
        oCRExtractionEngineInfo.createdAt = today;
      }

      this.updateForm(oCRExtractionEngineInfo);

      this.modelInfoService
        .query({ filter: 'ocrextractionengineinfo-is-null' })
        .pipe(
          map((res: HttpResponse<IModelInfo[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IModelInfo[]) => {
          if (!oCRExtractionEngineInfo.modelInfo || !oCRExtractionEngineInfo.modelInfo.id) {
            this.modelinfos = resBody;
          } else {
            this.modelInfoService
              .find(oCRExtractionEngineInfo.modelInfo.id)
              .pipe(
                map((subRes: HttpResponse<IModelInfo>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IModelInfo[]) => (this.modelinfos = concatRes));
          }
        });
    });
  }

  updateForm(oCRExtractionEngineInfo: IOCRExtractionEngineInfo): void {
    this.editForm.patchValue({
      id: oCRExtractionEngineInfo.id,
      name: oCRExtractionEngineInfo.name,
      userName: oCRExtractionEngineInfo.userName,
      password: oCRExtractionEngineInfo.password,
      apiKey: oCRExtractionEngineInfo.apiKey,
      createdAt: oCRExtractionEngineInfo.createdAt ? oCRExtractionEngineInfo.createdAt.format(DATE_TIME_FORMAT) : null,
      createdBy: oCRExtractionEngineInfo.createdBy,
      modelInfo: oCRExtractionEngineInfo.modelInfo,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const oCRExtractionEngineInfo = this.createFromForm();
    if (oCRExtractionEngineInfo.id !== undefined) {
      this.subscribeToSaveResponse(this.oCRExtractionEngineInfoService.update(oCRExtractionEngineInfo));
    } else {
      this.subscribeToSaveResponse(this.oCRExtractionEngineInfoService.create(oCRExtractionEngineInfo));
    }
  }

  private createFromForm(): IOCRExtractionEngineInfo {
    return {
      ...new OCRExtractionEngineInfo(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      userName: this.editForm.get(['userName'])!.value,
      password: this.editForm.get(['password'])!.value,
      apiKey: this.editForm.get(['apiKey'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      modelInfo: this.editForm.get(['modelInfo'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOCRExtractionEngineInfo>>): void {
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

  trackById(index: number, item: IModelInfo): any {
    return item.id;
  }
}
