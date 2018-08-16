package com.lnews.evgen.data.util.firebase;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import javax.inject.Inject;

public class RxFirestore {

    @Inject
    public RxFirestore() {

    }

    public Maybe<DocumentSnapshot> getDocument(final DocumentReference ref) {
        return Maybe.create(emitter -> ref.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                emitter.onSuccess(documentSnapshot);
            } else {
                emitter.onComplete();
            }
        }).addOnFailureListener(e -> {
            if (!emitter.isDisposed()) emitter.onError(e);
        }));
    }
}
