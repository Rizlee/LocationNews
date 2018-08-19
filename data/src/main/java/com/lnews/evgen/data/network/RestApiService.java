package com.lnews.evgen.data.network;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.lnews.evgen.data.util.firebase.RxFirebaseAuth;
import com.lnews.evgen.data.util.firebase.RxFirestore;
import com.lnews.evgen.domain.entities.Category;
import com.lnews.evgen.domain.entities.RootObject;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import javax.inject.Singleton;
import com.google.firebase.auth.FirebaseAuth;

@Singleton
public class RestApiService {
    private static final String CATEGORY_TAG = "category";

    private final RestApi restApi;
    private final RxFirebaseAuth rxFirebaseAuth;
    private final RxFirestore rxFirestore;

    @Inject
    RestApiService(RestApi restRestApi, RxFirebaseAuth rxFirebaseAuth, RxFirestore rxFirestore) {
        this.restApi = restRestApi;
        this.rxFirebaseAuth = rxFirebaseAuth;
        this.rxFirestore = rxFirestore;
    }

    public Maybe<AuthResult> register(String email, String password) {
        return rxFirebaseAuth.createUserWithEmailAndPassword(FirebaseAuth.getInstance(), email,
            password);
    }

    public Maybe<AuthResult> auth(String email, String password) {
        return rxFirebaseAuth.signInWithEmailAndPassword(FirebaseAuth.getInstance(), email,
            password);
    }

    public Completable sendResetRequest(String email) {
        return rxFirebaseAuth.sendPasswordResetEmail(FirebaseAuth.getInstance(), email);
    }

    public String getToken() {
        return Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
    }

    public Single<RootObject> getNewsByCategory(String country, String category) {
        return restApi.getNewsByCategory(country, category);
    }

    public void saveCategories(String token, List<String> categories) {
        DocumentReference categoryDocument = FirebaseFirestore.getInstance().collection(token).document(CATEGORY_TAG);
        categoryDocument.delete();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < categories.size(); i++){
           stringBuilder.append(categories.get(i));
           stringBuilder.append("_");
        }
        categoryDocument.set(new Category(stringBuilder.toString()));
    }

    public Maybe<DocumentSnapshot> getCategories(String token){
        DocumentReference categoryDocument = FirebaseFirestore.getInstance().collection(token).document(CATEGORY_TAG);
        return rxFirestore.getDocument(categoryDocument);
    }
}
