import { Moment } from 'moment';
import { IAuditLogDetails } from 'app/shared/model/audit-log-details.model';
import { IInvoice } from 'app/shared/model/invoice.model';

export interface IAuditLog {
  id?: number;
  activityName?: string;
  activityCreationDate?: Moment;
  activityStartTime?: Moment;
  activityEndTime?: Moment;
  assignedDate?: Moment;
  user?: string;
  status?: string;
  reason?: string;
  comments?: string;
  completeOn?: Moment;
  duration?: number;
  createdAt?: Moment;
  createdBy?: string;
  auditLogDetails?: IAuditLogDetails[];
  invoice?: IInvoice;
}

export class AuditLog implements IAuditLog {
  constructor(
    public id?: number,
    public activityName?: string,
    public activityCreationDate?: Moment,
    public activityStartTime?: Moment,
    public activityEndTime?: Moment,
    public assignedDate?: Moment,
    public user?: string,
    public status?: string,
    public reason?: string,
    public comments?: string,
    public completeOn?: Moment,
    public duration?: number,
    public createdAt?: Moment,
    public createdBy?: string,
    public auditLogDetails?: IAuditLogDetails[],
    public invoice?: IInvoice
  ) {}
}
