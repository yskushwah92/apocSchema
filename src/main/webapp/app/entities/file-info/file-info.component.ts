import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFileInfo } from 'app/shared/model/file-info.model';
import { FileInfoService } from './file-info.service';
import { FileInfoDeleteDialogComponent } from './file-info-delete-dialog.component';

@Component({
  selector: 'jhi-file-info',
  templateUrl: './file-info.component.html',
})
export class FileInfoComponent implements OnInit, OnDestroy {
  fileInfos?: IFileInfo[];
  eventSubscriber?: Subscription;

  constructor(protected fileInfoService: FileInfoService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.fileInfoService.query().subscribe((res: HttpResponse<IFileInfo[]>) => (this.fileInfos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInFileInfos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IFileInfo): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInFileInfos(): void {
    this.eventSubscriber = this.eventManager.subscribe('fileInfoListModification', () => this.loadAll());
  }

  delete(fileInfo: IFileInfo): void {
    const modalRef = this.modalService.open(FileInfoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.fileInfo = fileInfo;
  }
}
