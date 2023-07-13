import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddnewreviewerComponent } from './addnewreviewer.component';

describe('AddnewreviewerComponent', () => {
  let component: AddnewreviewerComponent;
  let fixture: ComponentFixture<AddnewreviewerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddnewreviewerComponent]
    });
    fixture = TestBed.createComponent(AddnewreviewerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
