import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatebookfulldescriptionComponent } from './updatebookfulldescription.component';

describe('UpdatebookfulldescriptionComponent', () => {
  let component: UpdatebookfulldescriptionComponent;
  let fixture: ComponentFixture<UpdatebookfulldescriptionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdatebookfulldescriptionComponent]
    });
    fixture = TestBed.createComponent(UpdatebookfulldescriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
