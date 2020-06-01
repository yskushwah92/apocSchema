import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IModelInfo } from 'app/shared/model/model-info.model';

@Component({
  selector: 'jhi-model-info-detail',
  templateUrl: './model-info-detail.component.html',
})
export class ModelInfoDetailComponent implements OnInit {
  modelInfo: IModelInfo | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ modelInfo }) => (this.modelInfo = modelInfo));
  }

  previousState(): void {
    window.history.back();
  }
}
