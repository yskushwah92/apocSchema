import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFileSourceDetails } from 'app/shared/model/file-source-details.model';
import { FileSourceDetailsService } from './file-source-details.service';
import { FileSourceDetailsDeleteDialogComponent } from './file-source-details-delete-dialog.component';

@Component({
  selector: 'jhi-file-source-details',
  templateUrl: './file-source-details.component.html',
})
export class FileSourceDetailsComponent implements OnInit, OnDestroy {
  fileSourceDetails?: IFileSourceDetails[];
  eventSubscriber?: Subscription;

  constructor(
    protected fileSourceDetailsService: FileSourceDetailsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.fileSourceDetailsService.query().subscribe((res: HttpResponse<IFileSourceDetails[]>) => (this.fileSourceDetails = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInFileSourceDetails();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IFileSourceDetails): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInFileSourceDetails(): void {
    this.eventSubscriber = this.eventManager.subscribe('fileSourceDetailsListModification', () => this.loadAll());
  }

  delete(fileSourceDetails: IFileSourceDetails): void {
    const modalRef = this.modalService.open(FileSourceDetailsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.fileSourceDetails = fileSourceDetails;
  }
}
