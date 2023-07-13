import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetbytitleComponent } from './getbytitle.component';

describe('GetbytitleComponent', () => {
  let component: GetbytitleComponent;
  let fixture: ComponentFixture<GetbytitleComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GetbytitleComponent]
    });
    fixture = TestBed.createComponent(GetbytitleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
