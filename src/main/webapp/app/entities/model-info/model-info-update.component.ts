import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IModelInfo, ModelInfo } from 'app/shared/model/model-info.model';
import { ModelInfoService } from './model-info.service';
import { IOCRRawExtraction } from 'app/shared/model/ocr-raw-extraction.model';
import { OCRRawExtractionService } from 'app/entities/ocr-raw-extraction/ocr-raw-extraction.service';

@Component({
  selector: 'jhi-model-info-update',
  templateUrl: './model-info-update.component.html',
})
export class ModelInfoUpdateComponent implements OnInit {
  isSaving = false;
  ocrrawextractions: IOCRRawExtraction[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    version: [],
    location: [],
    executionScript: [],
    createdAt: [],
    createdBy: [],
    oCRRawExtraction: [],
  });

  constructor(
    protected modelInfoService: ModelInfoService,
    protected oCRRawExtractionService: OCRRawExtractionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ modelInfo }) => {
      if (!modelInfo.id) {
        const today = moment().startOf('day');
        modelInfo.createdAt = today;
      }

      this.updateForm(modelInfo);

      this.oCRRawExtractionService
        .query({ filter: 'modelinfo-is-null' })
        .pipe(
          map((res: HttpResponse<IOCRRawExtraction[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IOCRRawExtraction[]) => {
          if (!modelInfo.oCRRawExtraction || !modelInfo.oCRRawExtraction.id) {
            this.ocrrawextractions = resBody;
          } else {
            this.oCRRawExtractionService
              .find(modelInfo.oCRRawExtraction.id)
              .pipe(
                map((subRes: HttpResponse<IOCRRawExtraction>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IOCRRawExtraction[]) => (this.ocrrawextractions = concatRes));
          }
        });
    });
  }

  updateForm(modelInfo: IModelInfo): void {
    this.editForm.patchValue({
      id: modelInfo.id,
      name: modelInfo.name,
      version: modelInfo.version,
      location: modelInfo.location,
      executionScript: modelInfo.executionScript,
      createdAt: modelInfo.createdAt ? modelInfo.createdAt.format(DATE_TIME_FORMAT) : null,
      createdBy: modelInfo.createdBy,
      oCRRawExtraction: modelInfo.oCRRawExtraction,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const modelInfo = this.createFromForm();
    if (modelInfo.id !== undefined) {
      this.subscribeToSaveResponse(this.modelInfoService.update(modelInfo));
    } else {
      this.subscribeToSaveResponse(this.modelInfoService.create(modelInfo));
    }
  }

  private createFromForm(): IModelInfo {
    return {
      ...new ModelInfo(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      version: this.editForm.get(['version'])!.value,
      location: this.editForm.get(['location'])!.value,
      executionScript: this.editForm.get(['executionScript'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      oCRRawExtraction: this.editForm.get(['oCRRawExtraction'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IModelInfo>>): void {
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

  trackById(index: number, item: IOCRRawExtraction): any {
    return item.id;
  }
}
