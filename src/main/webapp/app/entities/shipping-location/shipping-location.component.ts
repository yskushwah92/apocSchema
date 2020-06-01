import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IShippingLocation } from 'app/shared/model/shipping-location.model';
import { ShippingLocationService } from './shipping-location.service';
import { ShippingLocationDeleteDialogComponent } from './shipping-location-delete-dialog.component';

@Component({
  selector: 'jhi-shipping-location',
  templateUrl: './shipping-location.component.html',
})
export class ShippingLocationComponent implements OnInit, OnDestroy {
  shippingLocations?: IShippingLocation[];
  eventSubscriber?: Subscription;

  constructor(
    protected shippingLocationService: ShippingLocationService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.shippingLocationService.query().subscribe((res: HttpResponse<IShippingLocation[]>) => (this.shippingLocations = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInShippingLocations();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IShippingLocation): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInShippingLocations(): void {
    this.eventSubscriber = this.eventManager.subscribe('shippingLocationListModification', () => this.loadAll());
  }

  delete(shippingLocation: IShippingLocation): void {
    const modalRef = this.modalService.open(ShippingLocationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.shippingLocation = shippingLocation;
  }
}
