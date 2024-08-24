package com.example.foodplanner.calender.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.foodplanner.calender.pojo.MealPlan;
import com.example.foodplanner.db.LocalDataSource;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public class PlanRepository {

    private LocalDataSource localDataSource;
    private FirebaseFirestore firestore;

    public PlanRepository(LocalDataSource localDataSource) {
        this.localDataSource = localDataSource;
        firestore = FirebaseFirestore.getInstance();
    }

    public Observable<List<MealPlan>> getMealOfPlan(String userId, String mealOfDay) {
        return localDataSource.getMealOfPlan(userId, mealOfDay);
    }

    public Observable<List<MealPlan>> getPlansFromFirebase(String userId) {
        MutableLiveData<List<MealPlan>> liveData = new MutableLiveData<>();
        firestore.collection("users")
                .document(userId)
                .collection("week_plan")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        List<MealPlan> mealList = new ArrayList<>();
                        QuerySnapshot querySnapshot = task.getResult();
                        for (QueryDocumentSnapshot document : querySnapshot) {
                            MealPlan meal = document.toObject(MealPlan.class);
                            mealList.add(meal);
                        }
                        liveData.setValue(mealList);
                    } else {
                        Log.e("PlanRepository", "Error getting documents: ", task.getException());
                        liveData.setValue(new ArrayList<>());
                    }
                });

        return fromLiveData(liveData);
    }

    // Helper method to convert LiveData to Observable
    private <T> Observable<T> fromLiveData(LiveData<T> liveData) {
        return Observable.create(emitter -> {
            final Observer<T> observer = new Observer<T>() {
                @Override
                public void onChanged(T t) {
                    if (!emitter.isDisposed()) {
                        emitter.onNext(t);
                    }
                }
            };

            liveData.observeForever(observer);

            emitter.setCancellable(() -> liveData.removeObserver(observer));
        });
    }

    public Completable deleteMealFromPlan(MealPlan mealPlan) {
        return localDataSource.deleteMealFromPlan(mealPlan);
    }
}
