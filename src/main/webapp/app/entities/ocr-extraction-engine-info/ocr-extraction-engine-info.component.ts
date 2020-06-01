import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOCRExtractionEngineInfo } from 'app/shared/model/ocr-extraction-engine-info.model';
import { OCRExtractionEngineInfoService } from './ocr-extraction-engine-info.service';
import { OCRExtractionEngineInfoDeleteDialogComponent } from './ocr-extraction-engine-info-delete-dialog.component';

@Component({
  selector: 'jhi-ocr-extraction-engine-info',
  templateUrl: './ocr-extraction-engine-info.component.html',
})
export class OCRExtractionEngineInfoComponent implements OnInit, OnDestroy {
  oCRExtractionEngineInfos?: IOCRExtractionEngineInfo[];
  eventSubscriber?: Subscription;

  constructor(
    protected oCRExtractionEngineInfoService: OCRExtractionEngineInfoService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.oCRExtractionEngineInfoService
      .query()
      .subscribe((res: HttpResponse<IOCRExtractionEngineInfo[]>) => (this.oCRExtractionEngineInfos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInOCRExtractionEngineInfos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IOCRExtractionEngineInfo): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInOCRExtractionEngineInfos(): void {
    this.eventSubscriber = this.eventManager.subscribe('oCRExtractionEngineInfoListModification', () => this.loadAll());
  }

  delete(oCRExtractionEngineInfo: IOCRExtractionEngineInfo): void {
    const modalRef = this.modalService.open(OCRExtractionEngineInfoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.oCRExtractionEngineInfo = oCRExtractionEngineInfo;
  }
}
