import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOCRExtractionEngineInfo } from 'app/shared/model/ocr-extraction-engine-info.model';

@Component({
  selector: 'jhi-ocr-extraction-engine-info-detail',
  templateUrl: './ocr-extraction-engine-info-detail.component.html',
})
export class OCRExtractionEngineInfoDetailComponent implements OnInit {
  oCRExtractionEngineInfo: IOCRExtractionEngineInfo | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ oCRExtractionEngineInfo }) => (this.oCRExtractionEngineInfo = oCRExtractionEngineInfo));
  }

  previousState(): void {
    window.history.back();
  }
}
