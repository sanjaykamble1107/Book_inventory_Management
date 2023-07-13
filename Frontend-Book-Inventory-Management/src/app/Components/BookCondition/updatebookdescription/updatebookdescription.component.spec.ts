import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatebookdescriptionComponent } from './updatebookdescription.component';

describe('UpdatebookdescriptionComponent', () => {
  let component: UpdatebookdescriptionComponent;
  let fixture: ComponentFixture<UpdatebookdescriptionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdatebookdescriptionComponent]
    });
    fixture = TestBed.createComponent(UpdatebookdescriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
