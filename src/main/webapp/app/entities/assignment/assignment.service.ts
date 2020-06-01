import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAssignment } from 'app/shared/model/assignment.model';

type EntityResponseType = HttpResponse<IAssignment>;
type EntityArrayResponseType = HttpResponse<IAssignment[]>;

@Injectable({ providedIn: 'root' })
export class AssignmentService {
  public resourceUrl = SERVER_API_URL + 'api/assignments';

  constructor(protected http: HttpClient) {}

  create(assignment: IAssignment): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(assignment);
    return this.http
      .post<IAssignment>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(assignment: IAssignment): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(assignment);
    return this.http
      .put<IAssignment>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAssignment>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAssignment[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(assignment: IAssignment): IAssignment {
    const copy: IAssignment = Object.assign({}, assignment, {
      assignmentDate: assignment.assignmentDate && assignment.assignmentDate.isValid() ? assignment.assignmentDate.toJSON() : undefined,
      setSLA: assignment.setSLA && assignment.setSLA.isValid() ? assignment.setSLA.toJSON() : undefined,
      createdAt: assignment.createdAt && assignment.createdAt.isValid() ? assignment.createdAt.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.assignmentDate = res.body.assignmentDate ? moment(res.body.assignmentDate) : undefined;
      res.body.setSLA = res.body.setSLA ? moment(res.body.setSLA) : undefined;
      res.body.createdAt = res.body.createdAt ? moment(res.body.createdAt) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((assignment: IAssignment) => {
        assignment.assignmentDate = assignment.assignmentDate ? moment(assignment.assignmentDate) : undefined;
        assignment.setSLA = assignment.setSLA ? moment(assignment.setSLA) : undefined;
        assignment.createdAt = assignment.createdAt ? moment(assignment.createdAt) : undefined;
      });
    }
    return res;
  }
}
