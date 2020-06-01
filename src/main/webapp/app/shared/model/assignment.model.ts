import { Moment } from 'moment';
import { IInvoice } from 'app/shared/model/invoice.model';
import { AssignmentMode } from 'app/shared/model/enumerations/assignment-mode.model';

export interface IAssignment {
  id?: number;
  assignmentDate?: Moment;
  assignedTo?: string;
  assignedBy?: string;
  setSLA?: Moment;
  assignmentStatus?: string;
  percentageCompleted?: number;
  assignmentMode?: AssignmentMode;
  createdAt?: Moment;
  createdBy?: string;
  invoice?: IInvoice;
}

export class Assignment implements IAssignment {
  constructor(
    public id?: number,
    public assignmentDate?: Moment,
    public assignedTo?: string,
    public assignedBy?: string,
    public setSLA?: Moment,
    public assignmentStatus?: string,
    public percentageCompleted?: number,
    public assignmentMode?: AssignmentMode,
    public createdAt?: Moment,
    public createdBy?: string,
    public invoice?: IInvoice
  ) {}
}
