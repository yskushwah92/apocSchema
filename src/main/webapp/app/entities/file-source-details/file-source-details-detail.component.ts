import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFileSourceDetails } from 'app/shared/model/file-source-details.model';

@Component({
  selector: 'jhi-file-source-details-detail',
  templateUrl: './file-source-details-detail.component.html',
})
export class FileSourceDetailsDetailComponent implements OnInit {
  fileSourceDetails: IFileSourceDetails | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fileSourceDetails }) => (this.fileSourceDetails = fileSourceDetails));
  }

  previousState(): void {
    window.history.back();
  }
}
