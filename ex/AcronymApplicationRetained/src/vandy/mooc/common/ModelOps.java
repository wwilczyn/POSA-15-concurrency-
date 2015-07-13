package vandy.mooc.common;

/**
 * The base interface that an operations ("Ops") class in the
 * Model layer must implement.
 */
public interface ModelOps<RequiredModelOps> {
    /**
     * Hook method dispatched by the GenericModel framework to
     * initialize an operations ("Ops") object after it's been
     * instantiated.
     *
     * @param view
     *        The currently active RequiredModelOps.
     */
    void onCreate(RequiredModelOps view);

    /**
     * Hook method called when an Ops object in the Model layer is
     * destroyed.
     */
    void onDestroy();
}