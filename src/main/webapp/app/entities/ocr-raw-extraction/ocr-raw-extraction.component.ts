import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOCRRawExtraction } from 'app/shared/model/ocr-raw-extraction.model';
import { OCRRawExtractionService } from './ocr-raw-extraction.service';
import { OCRRawExtractionDeleteDialogComponent } from './ocr-raw-extraction-delete-dialog.component';

@Component({
  selector: 'jhi-ocr-raw-extraction',
  templateUrl: './ocr-raw-extraction.component.html',
})
export class OCRRawExtractionComponent implements OnInit, OnDestroy {
  oCRRawExtractions?: IOCRRawExtraction[];
  eventSubscriber?: Subscription;

  constructor(
    protected oCRRawExtractionService: OCRRawExtractionService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.oCRRawExtractionService.query().subscribe((res: HttpResponse<IOCRRawExtraction[]>) => (this.oCRRawExtractions = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInOCRRawExtractions();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IOCRRawExtraction): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInOCRRawExtractions(): void {
    this.eventSubscriber = this.eventManager.subscribe('oCRRawExtractionListModification', () => this.loadAll());
  }

  delete(oCRRawExtraction: IOCRRawExtraction): void {
    const modalRef = this.modalService.open(OCRRawExtractionDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.oCRRawExtraction = oCRRawExtraction;
  }
}
