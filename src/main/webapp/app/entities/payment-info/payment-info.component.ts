import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPaymentInfo } from 'app/shared/model/payment-info.model';
import { PaymentInfoService } from './payment-info.service';
import { PaymentInfoDeleteDialogComponent } from './payment-info-delete-dialog.component';

@Component({
  selector: 'jhi-payment-info',
  templateUrl: './payment-info.component.html',
})
export class PaymentInfoComponent implements OnInit, OnDestroy {
  paymentInfos?: IPaymentInfo[];
  eventSubscriber?: Subscription;

  constructor(
    protected paymentInfoService: PaymentInfoService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.paymentInfoService.query().subscribe((res: HttpResponse<IPaymentInfo[]>) => (this.paymentInfos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPaymentInfos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPaymentInfo): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPaymentInfos(): void {
    this.eventSubscriber = this.eventManager.subscribe('paymentInfoListModification', () => this.loadAll());
  }

  delete(paymentInfo: IPaymentInfo): void {
    const modalRef = this.modalService.open(PaymentInfoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.paymentInfo = paymentInfo;
  }
}
