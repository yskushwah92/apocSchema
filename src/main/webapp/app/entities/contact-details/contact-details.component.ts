import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IContactDetails } from 'app/shared/model/contact-details.model';
import { ContactDetailsService } from './contact-details.service';
import { ContactDetailsDeleteDialogComponent } from './contact-details-delete-dialog.component';

@Component({
  selector: 'jhi-contact-details',
  templateUrl: './contact-details.component.html',
})
export class ContactDetailsComponent implements OnInit, OnDestroy {
  contactDetails?: IContactDetails[];
  eventSubscriber?: Subscription;

  constructor(
    protected contactDetailsService: ContactDetailsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.contactDetailsService.query().subscribe((res: HttpResponse<IContactDetails[]>) => (this.contactDetails = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInContactDetails();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IContactDetails): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInContactDetails(): void {
    this.eventSubscriber = this.eventManager.subscribe('contactDetailsListModification', () => this.loadAll());
  }

  delete(contactDetails: IContactDetails): void {
    const modalRef = this.modalService.open(ContactDetailsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.contactDetails = contactDetails;
  }
}
