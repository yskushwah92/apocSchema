import { Moment } from 'moment';
import { IAuditLog } from 'app/shared/model/audit-log.model';

export interface IAuditLogDetails {
  id?: number;
  assignedBy?: string;
  actor?: string;
  status?: string;
  statusCode?: string;
  reason?: string;
  reasonCode?: string;
  createdOn?: Moment;
  comments?: string;
  delegate?: string;
  delegatedFlag?: boolean;
  metSLA?: string;
  createdAt?: Moment;
  createdBy?: string;
  auditLog?: IAuditLog;
}

export class AuditLogDetails implements IAuditLogDetails {
  constructor(
    public id?: number,
    public assignedBy?: string,
    public actor?: string,
    public status?: string,
    public statusCode?: string,
    public reason?: string,
    public reasonCode?: string,
    public createdOn?: Moment,
    public comments?: string,
    public delegate?: string,
    public delegatedFlag?: boolean,
    public metSLA?: string,
    public createdAt?: Moment,
    public createdBy?: string,
    public auditLog?: IAuditLog
  ) {
    this.delegatedFlag = this.delegatedFlag || false;
  }
}
