import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOCRRawExtraction } from 'app/shared/model/ocr-raw-extraction.model';

@Component({
  selector: 'jhi-ocr-raw-extraction-detail',
  templateUrl: './ocr-raw-extraction-detail.component.html',
})
export class OCRRawExtractionDetailComponent implements OnInit {
  oCRRawExtraction: IOCRRawExtraction | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ oCRRawExtraction }) => (this.oCRRawExtraction = oCRRawExtraction));
  }

  previousState(): void {
    window.history.back();
  }
}
