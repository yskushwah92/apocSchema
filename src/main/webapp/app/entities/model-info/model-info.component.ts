import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IModelInfo } from 'app/shared/model/model-info.model';
import { ModelInfoService } from './model-info.service';
import { ModelInfoDeleteDialogComponent } from './model-info-delete-dialog.component';

@Component({
  selector: 'jhi-model-info',
  templateUrl: './model-info.component.html',
})
export class ModelInfoComponent implements OnInit, OnDestroy {
  modelInfos?: IModelInfo[];
  eventSubscriber?: Subscription;

  constructor(protected modelInfoService: ModelInfoService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.modelInfoService.query().subscribe((res: HttpResponse<IModelInfo[]>) => (this.modelInfos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInModelInfos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IModelInfo): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInModelInfos(): void {
    this.eventSubscriber = this.eventManager.subscribe('modelInfoListModification', () => this.loadAll());
  }

  delete(modelInfo: IModelInfo): void {
    const modalRef = this.modalService.open(ModelInfoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.modelInfo = modelInfo;
  }
}
